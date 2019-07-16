package com.falanadamian.krim.schedule.security;

import com.falanadamian.krim.schedule.security.jwt.JWTAuthenticationEntryPoint;
import com.falanadamian.krim.schedule.security.jwt.JWTFilter;
import com.falanadamian.krim.schedule.security.jwt.JWTProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private ModelUserDetailsService modelUserDetailsService;
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JWTProvider jwtProvider;

    public WebSecurityConfig(ModelUserDetailsService modelUserDetailsService, JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint, JWTProvider jwtProvider) {
        this.modelUserDetailsService = modelUserDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter(this.jwtProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(modelUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/krim/authentication/**", "/socket/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
