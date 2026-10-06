package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ยังไม่มีระบบ login ทุก path เข้าได้โดยไม่ต้องยืนยันตัวตน (ใช้ Spring Security เฉพาะ CSRF กับ security header)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

                .csrf(csrf -> csrf
                        // เก็บ token ไว้ใน cookie (HttpOnly) แทน session แอปจึงไม่ต้องมี session และ restart ได้โดย token ไม่หาย
                        .csrfTokenRepository(new CookieCsrfTokenRepository())
                        // /api/** ถูกเรียกผ่าน api-gateway จากโปรแกรมอื่น ไม่ใช่จาก browser จึงไม่ต้องตรวจ CSRF
                        .ignoringRequestMatchers("/api/**"))

                // security header: ค่าเริ่มต้นของ Spring Security ใส่ให้อยู่แล้วคือ
                // X-Content-Type-Options: nosniff, X-Frame-Options: DENY, Cache-Control: no-cache
                // และ Strict-Transport-Security (HSTS) เฉพาะ request ที่เป็น HTTPS
                .headers(headers -> headers
                        // ให้ browser รัน JavaScript ได้เฉพาะไฟล์จากโดเมนเดียวกัน ห้าม inline script, eval และ script จากเว็บอื่น
                        .contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'"))
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .maxAgeInSeconds(31536000))); // 1 ปี

        return http.build();
    }

    // ไม่มี user ในระบบ ประกาศไว้เพื่อไม่ให้ Spring Boot สร้าง user เริ่มต้นพร้อม password สุ่มลง log
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }
}
