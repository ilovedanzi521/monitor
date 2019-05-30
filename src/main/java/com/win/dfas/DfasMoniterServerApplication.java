package com.win.dfas;

/**
 * 包名称：com.win.dfas
 * 类名称：Application
 * 类描述：${TODO}
 * 创建人：@author wanglei
 * 创建时间：2019/5/30/8:12
 */
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class DfasMoniterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfasMoniterServerApplication.class, args);
    }

}
