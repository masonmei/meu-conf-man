package com.baidu.oped.iop.m4.custom.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.baidu.oped.iop.m4.custom.auth.NullRecordLoginAttemptService;
import com.baidu.oped.iop.m4.custom.auth.NullRemoteAddressCheckingService;
import com.baidu.oped.iop.m4.custom.auth.RecordLoginAttemptService;
import com.baidu.oped.iop.m4.custom.auth.RemoteAddressAuthenticationProvider;
import com.baidu.oped.iop.m4.custom.auth.RemoteAddressCheckingService;
import com.baidu.oped.iop.m4.custom.auth.RemoteAddressPreAuthenticationFilter;
import com.baidu.oped.sia.boot.exception.RestSystemExceptionHandler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * @author mason
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class RemoteAddressSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private RestSystemExceptionHandler restSystemExceptionHandler;

    @Bean
    @ConditionalOnMissingBean
    public RecordLoginAttemptService recordLoginAttemptService() {
        return new NullRecordLoginAttemptService();
    }

    @Bean
    @ConditionalOnMissingBean
    public RemoteAddressCheckingService remoteAddressCheckingService() {
        return new NullRemoteAddressCheckingService();
    }

    @Bean
    public RemoteAddressAuthenticationProvider authenticationProvider() {
        RemoteAddressAuthenticationProvider authenticationProvider = new RemoteAddressAuthenticationProvider();
        authenticationProvider.setCheckingService(remoteAddressCheckingService());
        //        authenticationProvider.setDelegate();
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/application/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/products/**")
                .authenticated()
                .and()
                .addFilterAfter(remoteAddressAuthenticationFilter(), LogoutFilter.class);
    }

    private RemoteAddressPreAuthenticationFilter remoteAddressAuthenticationFilter() throws Exception {
        RemoteAddressPreAuthenticationFilter authenticationFilter = new RemoteAddressPreAuthenticationFilter();
        authenticationFilter.setCheckingService(remoteAddressCheckingService());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        ErrorPageFilter filter = new ErrorPageFilter();
        filter.addErrorPages(new ErrorPage("/application/error"));
        return filter;
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return registrationBean;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
