package cn.duyunzhi.security.config;


import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
/**
* @Author: Ezreal-d
* @Description: 错误页面配置
* @Date: 2019/9/3 14:42
*/

@Configuration
public class ErrorPageConfig {

    /**
     * 以编程方式配置嵌入式servlet容器，可以通过注册实现该 WebServerFactoryCustomizer 接口的Spring bean
     * TomcatServletWebServerFactory，JettyServletWebServerFactory并且UndertowServletWebServerFactory 是专用变体，
     * ConfigurableServletWebServerFactory分别为Tomcat，Jetty和Undertow提供了额外的自定义setter方法。
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {

            ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,
                    "/error-400");
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,
                    "/error-404");
            ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN,
                    "/error-403");
            ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                    "/error-500");
            factory.addErrorPages(errorPage400, errorPage404,errorPage403,
                    errorPage500);
        };
    }

}
