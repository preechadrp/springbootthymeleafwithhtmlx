package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller 
public class TestWaitController {

    @GetMapping("/TestSetCookie")
    public String testSetCokie(HttpServletResponse response, Model model) {

        // สร้าง cookie แล้วส่งกลับไปให้ browser ผ่าน response
        String cookieName = "myCookie";
        String cookieValue = "spring-boot-example";
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(30 * 60); // มีอายุ 30 นาที
        cookie.setPath("/"); // ใช้งานได้ทุก path ของ application
        cookie.setHttpOnly(true); // ป้องกัน JavaScript อ่าน cookie โดยตรง
        response.addCookie(cookie);

        model.addAttribute(cookieName, cookieValue);

        return "testwait-form";
    }


    @GetMapping("/TestWait")
    public String showUi(HttpServletRequest request, Model model) {

        // อ่าน cookie ชื่อ myCookie จาก request
        String cookieName = "myCookie";
        String cookieValue = "ไม่มี cookie";
        Cookie[] cookies = request.getCookies(); // เป็น null ถ้า request ไม่มี cookie เลย
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        log.info("myCookie = {}", cookieValue);
        model.addAttribute("myCookie", cookieValue);

        return "testwait-form";
    }

    @GetMapping("/TestWait2")
    public String showUi2(
            // อ่าน cookie ชื่อ myCookie ด้วย @CookieValue ถ้าไม่มี cookie จะใช้ค่า defaultValue แทน
            @CookieValue(name = "myCookie", defaultValue = "ไม่มี cookie") String myCookie,
            Model model) {

        log.info("myCookie = {}", myCookie);
        model.addAttribute("myCookie", myCookie);

        return "testwait-form";
    }

     @GetMapping("/TestWait-process")
	public String process(Model model) throws InterruptedException {

		// จำลองสถานการณ์ว่าดึงข้อมูลจาก Database นาน 2 วินาที
        Thread.sleep(5000); 
        
        model.addAttribute("totalUsers", 1542);
        model.addAttribute("dailySales", "฿45,000");
        
        // ส่งกลับไปเฉพาะ Fragment ชื่อ 'statsFragment'
        return "fragments/testwait-result :: myFragment";
	}
    
}
