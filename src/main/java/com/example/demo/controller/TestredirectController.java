package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/webui")
public class TestredirectController {
    
    @GetMapping("/testredirect")
    public String showUi() {
        return "testredirect-form"; 
    }

    @PostMapping("/testredirect-process")
	public void process2(HttpServletResponse response) throws InterruptedException {
		// จำลองสถานการณ์ว่าทำการประมวลผลงานนาน 2 วินาที
        Thread.sleep(2000); 
      
        // จำลองการประมวลผลข้อมูลที่ต้องการส่งเป็น Parameter
        String userId = "U998877";
        String processStatus = "success";
        String message = "โหลดเสร็จแล้ว"; // ภาษาไทยจะถูก Encode ให้อัตโนมัติ
        
        // สร้าง URL และแนบ Parameters ด้วย UriComponentsBuilder
        String redirectUrl = UriComponentsBuilder
                .fromUriString("https://www.google.com/")
                .queryParam("id", userId)
                .queryParam("status", processStatus)
                .queryParam("msg", message)
                .toUriString();
                
        // ผลลัพธ์ที่ได้จะเป็น: 
        // https://www.google.com/?id=U998877&status=success&msg=%E0%B9%82%E0%B8%AB...

        // สั่งให้ HTMX Redirect ไปยัง URL ที่สร้างเสร็จแล้ว
        response.setHeader("HX-Redirect", redirectUrl);

	}

}
