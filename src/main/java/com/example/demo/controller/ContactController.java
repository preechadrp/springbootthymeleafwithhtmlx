package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/")
    public String index() {
        return "index"; // โหลดหน้าหลักปกติ
    }

    @GetMapping("/contact/1")
    public String getContact(Model model) {
        // จำลองข้อมูลจาก Database
        model.addAttribute("name", "Somchai");
        model.addAttribute("email", "somchai@example.com");
        
        return "fragments/contact-info :: details"; // คืนค่าเฉพาะส่วน (Fragment)
    }
}