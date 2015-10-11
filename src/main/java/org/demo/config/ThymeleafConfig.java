package org.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * Конфигурирование Thymeleaf
 *
 * @author secundus
 * @since 23.08.15 3:17
 */
@Configuration
public class ThymeleafConfig {

    @Configuration
    @ConditionalOnClass({SpringSecurityDialect.class})
    protected static class ThymeleafSecurityDialectConfiguration {

        @Bean
        public SpringSecurityDialect springSecurityDialect() {
            return new SpringSecurityDialect();
        }
    }

}
