package io.vincent.gateway.outbound.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.nio.charset.Charset;

/**
 *@Author: Vincent Xu
 *@Description:
 *@CreatedTime:21:36 2020/11/2
 */ 
public class NettyHttpOutboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(NettyHttpOutboundHandler.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;
        String content = response.content().toString(Charset.forName("UTF-8"));
        System.out.println("Response: " + response.toString());
        System.out.println("Response content: " + content);
        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
        ctx.channel().attr(key).set(content);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("Error");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
//        URI url = new URI("/test");
//        String meg = "hello";
//
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
//        //发送数据
//        ctx.writeAndFlush(request);
    }
}
