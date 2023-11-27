package shop.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.app.config.filters.JwtRequestFilter;

@Configuration
public class JwtFilterConfig  {
    private final JwtRequestFilter jwtRequestFilter;

    public JwtFilterConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public FilterRegistrationBean jwtFilterRegister(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(jwtRequestFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setName("jwtFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
