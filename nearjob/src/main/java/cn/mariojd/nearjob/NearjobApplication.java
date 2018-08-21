package cn.mariojd.nearjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NearjobApplication {

    public static void main(String[] args) {
        // @See: https://www.jianshu.com/p/1d5257eecf08
         System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(NearjobApplication.class, args);
    }
}
