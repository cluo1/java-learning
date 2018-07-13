package cn.mariojd.socket;

import cn.mariojd.socket.netty.NettyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author jdq
 * @date 2018/4/21 15:14
 */
@SpringBootApplication
public class SocketFrameApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SocketFrameApplication.class, args);

        //启动Netty
        NettyApplication nettyApplication = context.getBean(NettyApplication.class);
        nettyApplication.start();
    }

}
