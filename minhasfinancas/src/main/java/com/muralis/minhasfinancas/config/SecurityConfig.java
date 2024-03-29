package com.muralis.minhasfinancas.config;

import com.muralis.minhasfinancas.api.JwtTokenFilter;
import com.muralis.minhasfinancas.service.JwtService;
import com.muralis.minhasfinancas.service.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityUserDetailService securityUserDetailService;

  @Autowired
  private JwtService jwtService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtTokenFilter jwtTokenFilter() {
    return new JwtTokenFilter(jwtService, securityUserDetailService);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(securityUserDetailService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/usuarios/autenticar").permitAll()
        .antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
        .anyRequest().authenticated()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilter(){

    List<String> all = Arrays.asList("*");

    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedMethods(all);
    config.setAllowedOrigins(all);
    config.setAllowedHeaders(all);
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    CorsFilter corFilter = new CorsFilter(source);

    FilterRegistrationBean<CorsFilter> filter =
            new FilterRegistrationBean<CorsFilter>(corFilter);
    filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

    return filter;
  }

}
