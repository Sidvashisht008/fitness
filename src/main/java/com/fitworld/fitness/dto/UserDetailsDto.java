package com.fitworld.fitness.dto;

public class UserDetailsDto {

	private Integer userId;
	private String sex;
	private Integer age;
	private Integer weight;
	private Integer height;
	private String phoneNo;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return "UserDetailsDto [userId=" + userId + ", sex=" + sex + ", age=" + age + ", weight=" + weight + ", height="
				+ height + ", phoneNo=" + phoneNo + "]";
	}
	
}
