package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller 
public class TestWaitController {

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
