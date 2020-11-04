package io.vincent.gateway.inbound;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *@Author: Vincent Xu
 *@Description: Init InboundHandler.
 *@CreatedTime:17:32 2020/10/30
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
    private String proxyServer;

    public HttpInboundInitializer(String proxyServer) {
        this.proxyServer = proxyServer;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        // ObjectAggregator size, netty send outbound message when reach this size or timeout. A proper size can handle more request.
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        channelPipeline.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
