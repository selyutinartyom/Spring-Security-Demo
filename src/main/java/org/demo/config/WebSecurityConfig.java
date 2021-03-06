package org.demo.config;

import org.demo.utils.H2UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Конфигурирование Spring Security.
 * Используется Spring Security адаптер конфигуратора.
 * <p>
 * Created by Selutin_AV on 05.08.2015.
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Конфигурация доступа к шаблонам
     *
     * @param http Устанавливает фильтры и связанные с ними сервисные компоненты, используемые
     *             для применения механизмов аутентификации, для защиты URL-ов, представления login и error страниц
     *             и многое другое
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/error", "/webjars/**", "/js/**", "/css/**", "/img/**", "/main/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /*
    Инстанс сервиса с пользователями. Сервис для базы данных H2.
     */
    @Autowired
    private H2UserDetailsService h2UserDetailsService;

    /**
     * Регистрируем нашу реализацию UserDetailsService.
     * Определение сервиса для соединения юзеров Spring Security и юзеров из базы данных.
     *
     * @param auth SecurityBuilder используется для создания AuthenticationManager.
     *             Позволяет легко строить in memory authentication, LDAP authentication, JDBC based authentication,
     *             добавление UserDetailsService и добавление AuthenticationProvider-ов.
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(h2UserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
