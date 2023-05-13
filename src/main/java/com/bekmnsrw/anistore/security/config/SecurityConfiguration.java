package com.bekmnsrw.anistore.security.config;

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

    private static final String[] WHITE_URL_LIST = { "/welcome", "/about", "/catalog" };
    private static final String[] ANONYMOUS_URL_LIST = { "/sign-in", "/sign-up" };
    private static final String[] ADMIN_URL_LIST = { "/admin/**" };
    private static final String[] AUTH_URL_LIST = { "/sign-out", "/profile", "/cart", "/order" };

    private static final String PROFILE_URL = "/profile";
    private static final String SIGN_IN_FAILURE_URL = "/sign-in?error";
    private static final String SIGN_IN_URL = "/sign-in";
    private static final String SIGN_OUT_URL = "/sign-out";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String JSESSIONID = "JSESSIONID";

    private static final String USERNAME_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsServiceImpl;
    private final DataSource dataSource;

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
                .defaultSuccessUrl(PROFILE_URL)
                .failureUrl(SIGN_IN_FAILURE_URL)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenRepository());

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
