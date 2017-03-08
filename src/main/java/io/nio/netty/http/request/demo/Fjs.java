package io.nio.netty.http.request.demo;

/**
 * @author: deadend
 * @date: 9:34 AM 1/13/17
 * @version: 1.0
 * @description:
 */


import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.netty.handler.codec.http.DefaultFullHttpRequest;


public class Fjs {
    public static AtomicInteger number = new AtomicInteger(0);
    public static AtomicLong time = new AtomicLong(0);

    public static void doIt(Channel channel) {
        if (number.get() < 50) {
            number.incrementAndGet();
            time.set(System.currentTimeMillis());
            QueryStringEncoder encoder = new QueryStringEncoder("http://127.0.0.1:8080/");
//            QueryStringEncoder encoder = new QueryStringEncoder("http://www.baidu.com/oapi/reqAd.jsp?pub=923875870&adspace=65826983&adcount=1&response=HTML&devip=22.56.22.66&user=900&format=IMG&position=top&height=&width=&device=Mozilla%2F5.0%20%28Linux%3B%20Android%204.2.1%3B%20en-us%3B%20Nexus%204%20Build%2FJOP40D%29%20AppleWebKit%2F535.19%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F18.0.1025.166%20Mobile%20Safari%2F535.19&beacon=TRUE&phpsnip=104");


            URI uriGet = null;
            try {
                uriGet = new URI(encoder.toString());
            } catch (URISyntaxException e) {
                System.out.println("我擦，，，，");
            }

            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uriGet.toASCIIString());
            channel.pipeline().write(request);
            channel.flush();
        } else {
            System.out.println("over");
        }
    }
    public static void main(String args[]) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        NioSocketChannel channel = new NioSocketChannel();  //创建一个channel，待会用它来发起链接
        channel.pipeline().addFirst(new InitHandler());   //为这个channel添加一个初始化的handler，用于响应待会channel建立成功
        group.register(channel);   //注册这个channel

        channel.connect(new InetSocketAddress("127.0.0.1", 8080));  //调用connect方法
        Thread.currentThread().sleep(Long.MAX_VALUE);


    }


    public static class InitHandler implements ChannelInboundHandler {

        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            // TODO Auto-generated method stub

        }

        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            // TODO Auto-generated method stub

        }

        public void channelRegistered(ChannelHandlerContext ctx)
                throws Exception {
            // TODO Auto-generated method stub

        }

        public void channelUnregistered(ChannelHandlerContext ctx)
                throws Exception {
            // TODO Auto-generated method stub

        }

        // 当连接建立成功之后会调用这个方法初始化channel
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            // TODO Auto-generated method stub
            ctx.channel().pipeline().remove(this);  //嗯，当前这个handler对这个channel就算是没有用了，可以移除了。。。
            ctx.channel().pipeline().addFirst(new HttpClientCodec());   //添加一个http协议的encoder与decoder
            ctx.channel().pipeline().addLast(new ResponseHandler());   //添加用于处理http返回信息的handler

            Fjs.doIt(ctx.channel());
        }

        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            // TODO Auto-generated method stub
            System.out.println("disconnect " + System.currentTimeMillis() / 1000);
        }

        public void channelRead(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            // TODO Auto-generated method stub
            System.out.println("read " + System.currentTimeMillis() / 1000);
        }

        public void channelReadComplete(ChannelHandlerContext ctx)
                throws Exception {
            // TODO Auto-generated method stub

        }

        public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
                throws Exception {
            // TODO Auto-generated method stub

        }

        public void channelWritabilityChanged(ChannelHandlerContext ctx)
                throws Exception {
            // TODO Auto-generated method stub

        }

        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                throws Exception {
            // TODO Auto-generated method stub
            System.out.println("error " + System.currentTimeMillis() / 1000);

        }

    }
}
