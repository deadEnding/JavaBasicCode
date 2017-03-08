package io.nio.netty.http.request;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

/**
 * @author: deadend
 * @date: 10:06 PM 1/12/17
 * @version: 1.0
 * @description:
 */


public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws URISyntaxException {
        for (int i = 0; i < 10; i++) {
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/prices/get?skuid=J_4281194");
            request.headers().set(HttpHeaderNames.HOST, "p.3.cn");
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            ctx.writeAndFlush(request);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        byte[] bytes = new byte[msg.content().readableBytes()];
        System.out.println(msg.status());
        msg.content().readBytes(bytes);
        System.out.println(new String(bytes));

//        if (new Random().nextInt(3) != 0) {
//            System.out.println("xxxx");
//            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
//            request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
//            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
//            ctx.writeAndFlush(request);
//        }
    }
}
