package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class TestWaitController {

    @GetMapping("/TestWait")
    public String showUi() {
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
