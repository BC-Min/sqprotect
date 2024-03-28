package sqproject.mbc.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf ->csrf.disable()).cors(cors ->cors.disable())
//                .authorizeHttpRequests(request -> request
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
//                )
//                .formLogin(login -> login	// form 방식 로그인 사용
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/index", true)	// 성공 시 dashboard로
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
//                )
//                .logout( logout -> { logout
//                        .logoutUrl("/logout")   // 로그아웃 처리 URL (= form action url)
//                        .logoutSuccessHandler((request, response, authentication) -> {
//                            response.sendRedirect("/");
//                        }) // 로그아웃 성공 핸들러
//                        .deleteCookies("remember-me"); // 로그아웃 후 삭제할 쿠키 지정
//                });
//
//        return http.build();
//    }
}