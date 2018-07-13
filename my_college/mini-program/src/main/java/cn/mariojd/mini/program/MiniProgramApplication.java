package cn.mariojd.mini.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot启动入口类
 */
@SpringBootApplication
@ComponentScan(value = {"cn.mariojd.mini.program", "com.luwei.module.shiro"})
public class MiniProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProgramApplication.class, args);
    }

}
