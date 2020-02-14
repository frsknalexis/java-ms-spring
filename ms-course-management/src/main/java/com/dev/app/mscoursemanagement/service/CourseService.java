package com.dev.app.mscoursemanagement.service;

import java.util.List;

import com.dev.app.mscoursemanagement.entity.Course;
import com.dev.app.mscoursemanagement.entity.Transaction;

public interface CourseService {

	List<Course> allCourses();
	
	List<Course> filterCoursesByIdList(List<Long> idList);
	
	List<Course> filterCourses(String content);
	
	List<Transaction> filterTransactionsOfUser(Long userId);
	
	List<Transaction> filterTransactionsOfCourse(Long courseId);
	
	void saveTransaction(Transaction transaction);
	
	Course findCourseById(Long courseId);
}
