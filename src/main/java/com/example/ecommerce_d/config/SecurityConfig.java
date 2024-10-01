package com.example.ecommerce_d.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private static final String[] PUBLIC_URLS = {
            "/",
            "/login",
            "/register",
            "/shop",
            "/index",
            "/saveUser",
//            "/api/**",
//            "/cart/**",
//            "/add-to-cart",
            "/checkout",
            "/admin/**",
            "/deleteUser",
            "/contact",
            "/testimonial",
            "/404",
            "/category",
            "/dashboard/**",
            "/web/**"
    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth->auth //chuỗi bộ lọc
//                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                        .requestMatchers("/cart/").hasAuthority("USER")
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .anyRequest()// mọi yêu cầu
                        .authenticated())
                .formLogin(login->
                        login.loginPage("/web/login")
                                .loginProcessingUrl("/process-login")// xu ly tai url process-login
                                .defaultSuccessUrl("/web/index", true)// duong dan se truy cap sau khi login
                                .permitAll()// form xác nhận
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để đăng xuất
                        .logoutSuccessUrl("/web/index") // URL chuyển hướng sau khi đăng xuất thành công
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable);// bảo mật
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();};

    @Bean
    public UserDetailsService userDetailsService(){//interface userDetailService để tải thông tin người dùng dựa trên username
        return new OurUserInfoDetailsService();// triển khai lớp để lấy thông tin người dùng
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){//interface thực hiện xác thực
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();// xác thực người dùng
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());//tai thong tin nguoi dung
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());//ma hoa mat khau
        return daoAuthenticationProvider;//xac thuc nguoi dung
    }

    @Bean
    WebSecurityCustomizer customizeWebSecurity(){
        return (web) -> web.ignoring().requestMatchers("/web_stt/**",
                "/dashboard_stt/**");
    }


}
