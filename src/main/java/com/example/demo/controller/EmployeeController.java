package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Employee;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

    @GetMapping("/form")
    public String showForm() {
        return "employee-form";
    }

    @PostMapping("/employee")
	public String addEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
		// ตรวจสอบข้อมูลฝั่ง server อีกชั้น เพราะ required ในฟอร์มกันได้แค่ฝั่ง browser
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getFieldErrors().stream()
					.map(FieldError::getDefaultMessage)
					.toList());
			return "fragments/result :: error-message";
		}

		// ในที่นี้เราส่งข้อมูลกลับไปแสดงเพื่อยืนยันว่าได้รับแล้ว
		model.addAttribute("message", "บันทึกข้อมูล " + employee.getName() + " สำเร็จ!");
		return "fragments/result :: success-message";
	}
}
