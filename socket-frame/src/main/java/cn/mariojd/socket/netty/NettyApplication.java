package cn.mariojd.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author jdq
 * @date 2018/4/3 11:14
 */
@Component
public class NettyApplication {

    private static final Logger logger = LoggerFactory.getLogger(NettyApplication.class);

    @Value("${netty.port}")
    private int port;

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childHandler(new NettyChannelInitializer());

            Channel channel = b.bind().sync().channel();
            channel.closeFuture().sync();
            logger.info("服务端开启端口 {} 等待客户端连接....", port);
        } catch (InterruptedException e) {
            logger.error(" Netty InterruptedException {}", e);
        } finally {
            //关闭程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
