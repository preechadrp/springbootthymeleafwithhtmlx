package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Employee;

@Controller
public class EmployeeController {

    @GetMapping("/form")
    public String showForm() {
        return "employee-form"; 
    }

    @PostMapping("/employee")
	public String addEmployee(Employee employee, Model model) {
		// ในที่นี้เราส่งข้อมูลกลับไปแสดงเพื่อยืนยันว่าได้รับแล้ว
		model.addAttribute("message", "บันทึกข้อมูล " + employee.getName() + " สำเร็จ!");
		return "fragments/result :: success-message";
	}
}