package io.aio.timeserver;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author: deadend
 * @date: 1:57 PM 1/10/17
 * @version: 1.0
 * @description:
 */


public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler>{

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
