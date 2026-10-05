package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee {
	@NotBlank(message = "กรุณากรอกชื่อ")
	@Size(max = 100, message = "ชื่อต้องยาวไม่เกิน 100 ตัวอักษร")
	private String name;

	@NotBlank(message = "กรุณากรอกตำแหน่ง")
	@Size(max = 100, message = "ตำแหน่งต้องยาวไม่เกิน 100 ตัวอักษร")
	private String role;
}
