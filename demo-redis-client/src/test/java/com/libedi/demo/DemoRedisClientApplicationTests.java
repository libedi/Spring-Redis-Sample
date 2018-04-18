package com.libedi.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	
	/**
	 * Test to delete redis entity
	 * @throws Exception
	 */
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
		Student student = new Student("ID001", "Sangjun,Park", Gender.MALE, 1, new Address("Seoul", "12345"));
		this.studentRepository.save(student);
		Optional<Student> retrieveStudent = this.studentRepository.findById("ID001");
		assertTrue("Not found Student entity.", retrieveStudent.isPresent());
	}
	
	/**
	 * Test to retrieve redis entity
	 * @throws Exception
	 */
	@Test
	public void test02_RetrieveStudent() throws Exception {
		// RedisHash() 에 TimeToLive 설정시 expired되어서 오류가 발생할 것이다.
//		Thread.sleep(2000L);
		Optional<Student> retrieveStudent = this.studentRepository.findById("ID001");
		assertTrue("Not found Student entity.", retrieveStudent.isPresent());
		assertEquals(Gender.MALE, retrieveStudent.get().getGender());
		assertEquals("12345", retrieveStudent.get().getAddress().getZipcode());
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
			student.setAddress(new Address("Seoul", "99999"));
			this.studentRepository.save(student);
		});
		Optional<Student> updateStudent = this.studentRepository.findById("ID001");
		assertTrue("Not found Student entity.", updateStudent.isPresent());
		assertEquals("Sinae, Oh", updateStudent.get().getName());
		assertEquals(Gender.FEMALE, updateStudent.get().getGender());
		assertEquals("99999", updateStudent.get().getAddress().getZipcode());
	}
	
	/**
	 * Test to count redis entity
	 * @throws Exception
	 */
	@Test
	public void test04_CountStudent() throws Exception {
		long count = this.studentRepository.count();
		System.out.println("Student Entity Count : " + count);
		assertTrue(count > 0);
	}

}
