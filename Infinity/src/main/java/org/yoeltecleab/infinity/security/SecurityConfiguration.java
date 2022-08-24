package org.yoeltecleab.infinity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.yoeltecleab.infinity.service.UserService;

/**
 * <pre>
 * This class contains the configurations for the project
 * allowing http requests and login forms and others
 * </pre>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/signup", "/index",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**").permitAll() //permits the files in these directories
                .antMatchers("/service**", "/booking**", "/billing**").authenticated()
                .anyRequest().permitAll() //permits landing on homepage without authentication
                .and()
                .formLogin()
                .loginPage("/login")//specifies the custom login page
                .permitAll().defaultSuccessUrl("/") //permits the login page
                .and()
                .logout() //configures the logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)  //clears the authentication of the user
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //the mapping required to logout
                .logoutSuccessUrl("/") //what will happen after logout is successful
                .permitAll();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}