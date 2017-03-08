package io.nio.netty.http.request.wrap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static io.netty.channel.ChannelFutureListener.CLOSE;

/**
 * @author: deadend
 * @date: 8:01 PM 1/13/17
 * @version: 1.0
 * @description:
 */


public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private static AtomicInteger success = new AtomicInteger(0);
    private static AtomicInteger failure = new AtomicInteger(0);

    private Semaphore sem;

    public HttpClientHandler(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        byte[] bytes = new byte[msg.content().readableBytes()];
        msg.content().readBytes(bytes);
//        System.out.println(msg.headers().entries().toString());
//        System.out.println(new String(bytes));
        if (msg.decoderResult().isSuccess()) {
            success.incrementAndGet();
        } else {
            failure.incrementAndGet();
        }
        ctx.close().addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                sem.release();
            }
        });
//        if ((success.get() + failure.get()) % 1000 == 0)
            System.out.println(success + " " + failure);
    }

//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("xxxx");
//    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        failure.incrementAndGet();
        cause.printStackTrace();
        sem.release();
        ctx.close();
    }
}
