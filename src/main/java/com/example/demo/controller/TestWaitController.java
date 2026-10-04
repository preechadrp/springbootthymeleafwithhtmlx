package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller 
public class TestWaitController {

    @GetMapping("/TestSetCokie")
    public String testSetCokie(HttpServletResponse response, Model model) {

        // สร้าง cookie แล้วส่งกลับไปให้ browser ผ่าน response
        String cookieName = "mycokie";
        String cookieValue = "spring-boot-example";
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(60 * 60); // มีอายุ 1 ชั่วโมง
        cookie.setPath("/"); // ใช้งานได้ทุก path ของ application
        cookie.setHttpOnly(true); // ป้องกัน JavaScript อ่าน cookie โดยตรง
        response.addCookie(cookie);

        model.addAttribute(cookieName, cookieValue);

        return "testwait-form";
    }


    @GetMapping("/TestWait")
    public String showUi(HttpServletRequest request, Model model) {

        // อ่าน cookie ชื่อ mycokie จาก request
        String myCokie = "ไม่มี cookie";
        Cookie[] cookies = request.getCookies(); // เป็น null ถ้า request ไม่มี cookie เลย
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("mycokie".equals(cookie.getName())) {
                    myCokie = cookie.getValue();
                    break;
                }
            }
        }

        System.out.println("mycokie = " + myCokie);
        model.addAttribute("myCokie", myCokie);

        return "testwait-form";
    }

     @GetMapping("/TestWait-process")
	public String process(Model model) throws InterruptedException {

		// จำลองสถานการณ์ว่าดึงข้อมูลจาก Database นาน 2 วินาที
        Thread.sleep(5000); 
        
        model.addAttribute("totalUsers", 1542);
        model.addAttribute("dailySales", "฿45,000");
        
        // ส่งกลับไปเฉพาะ Fragment ชื่อ 'statsFragment'
        return "/fragments/testwait-result :: myFragment";
	}
    
}
