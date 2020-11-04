package io.vincent.gateway.inbound;

import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;
import io.vincent.gateway.filter.IFilter;
import io.vincent.gateway.filter.SignFilter;
import io.vincent.gateway.outbound.httpClient.HttpOutboundHandler;
import io.vincent.gateway.outbound.netty.NettyHttpClient;
import io.vincent.gateway.utils.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 *@Author: Vincent Xu
 *@Description: Handle inbound request.
 *@CreatedTime:17:36 2020/10/30
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;
//    private HttpOutboundHandler handler;
    private NettyHttpClient nettyHttpClient;

    private IFilter filter = ApplicationContextUtil.getBean(IFilter.class);

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        //handler = new HttpOutboundHandler(this.proxyServer);
        nettyHttpClient= new NettyHttpClient(proxyServer);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        filter.run(fullRequest);
        //handler.handle(fullRequest, ctx);
        nettyHttpClient.connect(fullRequest, ctx);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //super.channelReadComplete(ctx);
        ctx.flush();
    }
}
