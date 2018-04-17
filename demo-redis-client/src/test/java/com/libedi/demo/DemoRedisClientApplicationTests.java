package com.libedi.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.libedi.demo.Student.Gender;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoRedisClientApplicationTests {
	
	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test00_DeleteStudent() throws Exception {
		this.studentRepository.findById("ID001").ifPresent(student -> {
			this.studentRepository.deleteById(student.getId());
		});
		Optional<Student> optStudent = this.studentRepository.findById("ID001");
		assertFalse(optStudent.isPresent());
	}
	
	/**
	 * Test to save redis entity
	 * @throws Exception
	 */
	@Test
	public void test01_SaveStudent() throws Exception {
		Student student = new Student("ID001", "Sangjun,Park", Gender.MALE, 1);
		this.studentRepository.save(student);
		Student retrieveStudent = this.studentRepository.findById("ID001").orElse(null);
		assertNotNull("Not found Student entity.", retrieveStudent);
	}
	
	/**
	 * Test to retrieve redis entity
	 * @throws Exception
	 */
	@Test
	public void test02_RetrieveStudent() throws Exception {
		Student retrieveStudent = this.studentRepository.findById("ID001").orElse(null);
		assertNotNull("Not found Student entity.", retrieveStudent);
		assertEquals(Gender.MALE, retrieveStudent.getGender());
	}
	
	/**
	 * Test to update redis entity
	 * @throws Exception
	 */
	@Test
	public void test03_UpdateStudent() throws Exception {
		 this.studentRepository.findById("ID001").ifPresent(student -> {
			student.setName("Sinae, Oh");
			student.setGender(Gender.FEMALE);
			this.studentRepository.save(student);
		});
		Student updateStudent = this.studentRepository.findById("ID001").orElse(null);
		assertNotNull("Not found Student entity.", updateStudent);
		assertEquals("Sinae, Oh", updateStudent.getName());
		assertEquals(Gender.FEMALE, updateStudent.getGender());
	}

}
