package br.com.ernanilima.jmercado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableAsync
@Configuration
@EnableScheduling
//@ComponentScan(basePackages = "br.com.ernanilima.jmercado",
//        excludeFilters=@ComponentScan.Filter(
//                type= FilterType.REGEX,
//                pattern={
//                    "br.com.ernanilima.jmercado.Model.*"
//                }
//        )
//)
public class ConfigAplicacao {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}