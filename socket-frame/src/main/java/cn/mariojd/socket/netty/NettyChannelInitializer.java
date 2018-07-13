package cn.mariojd.socket.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author jdq
 * @date 2018/4/3 11:36
 */
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Value("${netty.idle-time}")
    private int idleTime;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new IdleStateHandler(idleTime, idleTime, idleTime))
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new ChunkedWriteHandler())
                .addLast(new NettyChannelHandler());
    }

}
