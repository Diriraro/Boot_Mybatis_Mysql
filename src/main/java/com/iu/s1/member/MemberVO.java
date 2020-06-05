package com.iu.s1.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class MemberVO {
	//비어있으면 x
	@NotEmpty
	private String id;
	//비어있으면 x
	//4글자 이상 10글자 이하
	@NotEmpty
	@Size(min = 4, max = 10)
	private String password;
	
	private String pwCheck;
	
	public String getPwCheck() {
		return pwCheck;
	}
	public void setPwCheck(String pwCheck) {
		this.pwCheck = pwCheck;
	}
	//0살 이상 200살 이하
	@Range(min = 0, max = 200)
	@NotNull
	private Integer age;
	//email형식 (id@aaa.com)
	@Email
	@NotEmpty
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
