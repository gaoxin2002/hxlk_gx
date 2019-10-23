package com.hxlk.centre;

import com.hxlk.centre.service.MonitorService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

@MapperScan("com.hxlk.centre.mapper")
//@EnableAutoConfiguration
@SpringBootApplication
public class CentreApplication extends SpringBootServletInitializer {

    @Resource
    MonitorService monitorService;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//                monitorService.bind(9898);
        return builder.sources(CentreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CentreApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
////        monitorService.bind(9898);
//    }
    /*
    启动类实现支持跨域
 */
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
//        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
//        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 4
//        return new CorsFilter(source);
//    }
}
