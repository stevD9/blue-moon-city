package me.stef.bluemooncity.config;

import me.stef.bluemooncity.security.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/users", "/sessions").permitAll()
                        .requestMatchers("/admin*").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll)
                .csrf(AbstractHttpConfigurer::disable)
                .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer.key("666"));
//                .antMatchers("/anonymous*")
//                .anonymous()
//                .antMatchers("/login*")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/login")
//                .failureUrl("/login.html?error=true")
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .and()
//                .rememberMe()
//                .key("uniqueAndSecret");
        return http.build();
    }
}
