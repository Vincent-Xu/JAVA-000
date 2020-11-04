package io.vincent.gateway.outbound.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.vincent.gateway.outbound.httpClient.HttpOutboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 *@Author: Vincent Xu
 *@Description: Netty client
 *@CreatedTime:17:44 2020/11/2
 */
public class NettyHttpClient {
    // todo 增加log4j配置
    private static Logger logger = LoggerFactory.getLogger(NettyHttpClient.class);
    private String proxyServer;
    // todo 复用channel或者bootstrap
    private static Map<String, Channel> channelMap = new ConcurrentHashMap<>(16);

    public NettyHttpClient(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    public void connect(FullHttpRequest fullRequest, ChannelHandlerContext ctx) throws Exception {
        final EventLoopGroup workers = new NioEventLoopGroup(1);
        try {

            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.group(workers).channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    System.out.println("Connecting...");
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new HttpRequestEncoder());
                    pipeline.addLast(new HttpResponseDecoder());
                    pipeline.addLast(new HttpObjectAggregator(1024 * 10 * 1024));
                    pipeline.addLast(new NettyHttpOutboundHandler());
                }
            });
            ChannelFuture f = bootstrap.connect("localhost", 8808).sync();

            // use ChannelPool https://blog.csdn.net/yucharlie/article/details/76512313
            // send request
            f.channel().write(fullRequest);
            f.channel().flush();
            // channelMap.put("netty-server", f.channel());

            f.channel().closeFuture().sync();
            AttributeKey<String> key = AttributeKey.valueOf("ServerData");
            String content = f.channel().attr(key).get();
//            String header = fullRequest.headers().get("TIME_STAMP");
//            System.out.println("request header: "+ header);
//            System.out.println("content: "+ content);
//            if(!header.equals(content)) {
//                System.err.println("不相等");
//            } else {
//                System.err.println("相等");
//            }
            handle(fullRequest, ctx, content);
        } finally {
            workers.shutdownGracefully();
        }
    }

    public Channel getChanel(String serviceName) {
        if (channelMap.containsKey(serviceName)) {
            return channelMap.get(serviceName);
        } else {

        }
        return null;
    }

    public static void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String content) {
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content.getBytes("UTF-8")));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

        } catch (Exception e) {
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        URI url = new URI("/test");
//        String meg = "hello";
//        //配置HttpRequest的请求数据和一些配置信息
//        FullHttpRequest request = new DefaultFullHttpRequest(
//                HttpVersion.HTTP_1_0, HttpMethod.GET, url.toASCIIString(), Unpooled.wrappedBuffer(meg.getBytes("UTF-8")));
//
//        request.headers()
//                .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8")
//                //开启长连接
//                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
//                //设置传递请求内容的长度
//                .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
//
//        NettyHttpClient client = new NettyHttpClient("127.0.0.1:8808");
//        //启动client服务
//        client.connect(request, null);
//    }
}
