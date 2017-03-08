package io.nio.netty.http.request.demo;

/**
 * @author: deadend
 * @date: 9:35 AM 1/13/17
 * @version: 1.0
 * @description:
 */


import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;

public class ResponseHandler extends ChannelInboundHandlerAdapter{
    ByteBuf buf = Unpooled.buffer();

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            DefaultHttpResponse response = (DefaultHttpResponse)msg;
        }
        if (msg instanceof HttpContent) {
            DefaultLastHttpContent chunk = (DefaultLastHttpContent)msg;
            buf.writeBytes(chunk.content());
            if (chunk instanceof LastHttpContent) {
                long now = System.currentTimeMillis();
                long before = Fjs.time.get();
                System.out.println(((double) now - (double)before) / 1000);
                String xml = buf.toString(Charset.forName("UTF-8"));
                buf.clear();
                Fjs.doIt(ctx.channel());
            }
        }


    }
}
