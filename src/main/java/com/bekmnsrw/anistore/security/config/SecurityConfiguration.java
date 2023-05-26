package com.bekmnsrw.anistore.security.config;

import com.bekmnsrw.anistore.security.handler.AuthFailureHandler;
import com.bekmnsrw.anistore.security.handler.AuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String[] ANONYMOUS_URL_LIST = { "/sign-in", "/sign-up" };
    private static final String[] ADMIN_URL_LIST = { "/admin/**" };
    private static final String[] AUTH_URL_LIST = { "/sign-out", "/profile", "/cart", "/order" };
    private static final String SIGN_IN_URL = "/sign-in";
    private static final String SIGN_OUT_URL = "/sign-out";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private static final String JSESSIONID = "JSESSIONID";

    private static final String USERNAME_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String REMEMBER_ME_PARAMETER = "remember-me";

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsServiceImpl;
    private final DataSource dataSource;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((customizer) ->
                customizer
                        .requestMatchers(ANONYMOUS_URL_LIST).anonymous()
                        .requestMatchers(AUTH_URL_LIST).authenticated()
                        .requestMatchers(ADMIN_URL_LIST).hasAuthority(ADMIN_ROLE)
                        .anyRequest().permitAll()
        );

        httpSecurity.formLogin()
                .loginPage(SIGN_IN_URL)
                .loginProcessingUrl(SIGN_IN_URL)
                .usernameParameter(USERNAME_PARAMETER)
                .passwordParameter(PASSWORD_PARAMETER)
                .failureHandler(authFailureHandler)
                .successHandler(authSuccessHandler);
//                .and()
//                .rememberMe()
//                .rememberMeParameter(REMEMBER_ME_PARAMETER)
//                .tokenRepository(persistentTokenRepository());

        httpSecurity.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(SIGN_OUT_URL))
                .logoutSuccessUrl(SIGN_IN_URL)
                .clearAuthentication(true)
                .invalidateHttpSession(false)
                .deleteCookies(JSESSIONID);

        return httpSecurity.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(
            AuthenticationManagerBuilder authenticationManagerBuilder
    ) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
