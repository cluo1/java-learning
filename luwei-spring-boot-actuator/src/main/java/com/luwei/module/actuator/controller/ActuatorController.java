package com.luwei.module.actuator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author jdq
 * @date 2017/12/21 14:25
 * Docs: https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
 */
@Api(tags = "监控模块")
@RequestMapping("api/module/actuator")
@Controller
public class ActuatorController {

    @Value("${server.port}")
    private String port;

    @GetMapping("logfile")
    @ApiOperation("返回日志文件的内容（如果已设置logging.file或logging.path属性）。支持使用HTTP范围头来检索部分日志文件的内容。")
    @ApiIgnore
    public String logfile() {
        return "redirect:/logfile";
    }

    @GetMapping("jolokia")
    @ApiOperation("通过HTTP公开JMX bean（当Jolokia在类路径上时）")
    @ApiIgnore
    public String jolokia() {
        return "redirect:/jolokia";
    }

    @GetMapping("docs")
    @ApiOperation("显示执行器端点的文档，包括示例请求和响应。需要spring-boot-actuator-docs在类路径上")
    @ApiIgnore
    public String docs() {
        return "redirect:/docs";
    }

    @GetMapping("heap/dump")
    @ApiOperation("返回到GZip压缩的hprof堆转储文件")
    public String heapDump() {
        return "redirect:/heapdump";
    }

    @GetMapping("actuator")
    @ApiOperation("为其他端点提供基于超媒体的“发现页面”。要求Spring HATEOAS在类路径上")
    @ApiIgnore
    public String actuator() {
        return "redirect:/actuator";
    }

    @GetMapping("audit/events")
    @ApiOperation("公开当前应用程序的审计事件信息")
    public String auditEvents() {
        return "redirect:/auditevents";
    }

    @GetMapping("autoconfig")
    @ApiOperation("显示auto-configuration的报告，该报告展示所有auto-configuration候选者及它们被应用或未被应用的原因")
    public String autoconfig() {
        return "redirect:/autoconfig";
    }

    @GetMapping("beans")
    @ApiOperation("显示应用中所有Spring Beans的完整列表")
    public String beans() {
        return "redirect:/beans";
    }

    @GetMapping("configprops")
    @ApiOperation("显示应用中所有@ConfigurationProperties的整理列表")
    public String configprops() {
        return "redirect:/configprops";
    }

    @GetMapping("dump")
    @ApiOperation("执行线程转储")
    public String dump() {
        return "redirect:/dump";
    }

    @GetMapping("env")
    @ApiOperation("暴露来自Spring ConfigurableEnvironment的属性")
    public String env() {
        return "redirect:/env";
    }

    @GetMapping("flyway")
    @ApiOperation("显示已应用的所有Flyway数据库迁移")
    @ApiIgnore
    public String flyway() {
        return "redirect:/flyway";
    }

    @GetMapping("health")
    @ApiOperation("展示应用的健康信息（当使用一个未认证连接访问时显示一个简单的’status’，使用认证连接访问则显示全部信息详情）")
    public String health() {
        return "redirect:/health";
    }

    @GetMapping("info")
    @ApiOperation("显示任意的应用信息")
    public String info() {
        return "redirect:/info";
    }

    @GetMapping("loggers")
    @ApiOperation("显示和修改应用程序中的日志配置")
    public String loggers() {
        return "redirect:/loggers";
    }

    @GetMapping("liquibase")
    @ApiOperation("显示已经应用的任何Liquibase数据库迁移")
    @ApiIgnore
    public String liquibase() {
        return "redirect:/liquibase";
    }

    @GetMapping("metrics")
    @ApiOperation("显示当前应用程序的“指标”信息")
    public String metrics() {
        return "redirect:/metrics";
    }

    @GetMapping("mappings")
    @ApiOperation("显示所有@RequestMapping路径的整理列表")
    public String mappings() {
        return "redirect:/mappings";
    }

    @PostMapping("shutdown")
    @ApiOperation("允许应用以优雅的方式关闭（默认情况下不启用）")
    public void shutdown() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("http://localhost:" + port + "/shutdown", null);
    }

    @GetMapping("trace")
    @ApiOperation("显示跟踪信息（默认为最新的100条HTTP请求）")
    public String trace() {
        return "redirect:/trace";
    }

}
