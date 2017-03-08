package io.nio.netty.timeserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author: deadend
 * @date: 4:49 PM 1/10/17
 * @version: 1.0
 * @description:
 */


public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + "; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
        currentTime += System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
