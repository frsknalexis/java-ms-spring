package com.dev.app.mscoursemanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.mscoursemanagement.entity.Course;
import com.dev.app.mscoursemanagement.entity.Transaction;
import com.dev.app.mscoursemanagement.intercomm.LogClient;
import com.dev.app.mscoursemanagement.intercomm.UserClient;
import com.dev.app.mscoursemanagement.service.CourseService;

@RestController
@RequestMapping("/service")
public class CourseRestController {

	@Autowired
	private LogClient logClient;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private Environment env;
	
	@Value("${spring.application.name}")
	private String serviceId;
	
	@GetMapping("/port")
	String getPort() {
		return "Service is working at port: ".concat(env.getProperty("server.port"));
	}
	
	@GetMapping("/instances")
	ResponseEntity<?> getInstances() {
		return ResponseEntity.ok(discoveryClient.getInstances(serviceId));
	}
	
	@PostMapping("/user")
	ResponseEntity<?> filterTransactions(@RequestBody Long userId) {
		List<Transaction> transactionsOfUser = courseService.filterTransactionsOfUser(userId);
		return new ResponseEntity<>(transactionsOfUser, HttpStatus.OK);
	}
	
	@GetMapping("/popular")
	ResponseEntity<?> popularCourses() {
		List<Long> popularIdList = logClient.findPopularCourses();
		if (popularIdList == null || popularIdList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<Course> popularCourses = courseService.filterCoursesByIdList(popularIdList);
		return new ResponseEntity<>(popularCourses, HttpStatus.OK);
	}
	
	@PostMapping("/")
	ResponseEntity<?> allCourses() {
		List<Course> courses = courseService.allCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	ResponseEntity<?> filterCourses(@RequestBody String text) {
		List<Course> courses = courseService.filterCourses(text);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@PostMapping("/enroll")
	ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
		transaction.setDateOfIssue(LocalDateTime.now());
		transaction.setCourse(courseService.findCourseById(transaction.getCourse().getId()));
		courseService.saveTransaction(transaction);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/students")
	ResponseEntity<?> findCourseStudents(@RequestBody Long courseId) {
		List<Transaction> listTransaction = courseService.filterTransactionsOfCourse(courseId);
		if (listTransaction != null && !listTransaction.isEmpty()) {
			List<Long> userIdList = listTransaction.parallelStream()
										.map(t -> {
											return t.getUserId();
										}).collect(Collectors.toList());
			List<String> userNames = userClient.getNameOfUsers(userIdList);
			return new ResponseEntity<>(userNames, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
}
