package com.libedi.demo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * Redis Entity
 * @author Sangjun, Park
 *
 */
@RedisHash(value = "students", timeToLive = 10L)
public class Student implements Serializable {

	private static final long serialVersionUID = 5723343694201021910L;

	public enum Gender {
		MALE, FEMALE;
	}
	
	@Id
	private String id;
	private String name;
	private Gender gender;
	private int grade;
	private Address address;
	
	public Student(String id, String name, Gender gender, int grade, Address address) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
