package com.dev.app.mscoursemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev.app.mscoursemanagement.entity.Course;
import com.dev.app.mscoursemanagement.entity.Transaction;
import com.dev.app.mscoursemanagement.repository.CourseRepository;
import com.dev.app.mscoursemanagement.repository.TransactionRepository;
import com.dev.app.mscoursemanagement.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	@Qualifier("transactionRepository")
	private TransactionRepository transactionRepository;
	
	@Autowired
	@Qualifier("courseRepository")
	private CourseRepository courseRepository;

	@Override
	public List<Course> allCourses() {
		return courseRepository.findAll();
	}

	@Override
	public List<Course> filterCoursesByIdList(List<Long> idList) {
		return courseRepository.filterCoursesByIdList(idList);
	}

	@Override
	public List<Course> filterCourses(String content) {
		return courseRepository.filterCourses(content);
	}

	@Override
	public List<Transaction> filterTransactionsOfUser(Long userId) {
		return transactionRepository.findAllTransactionsOfUser(userId);
	}

	@Override
	public List<Transaction> filterTransactionsOfCourse(Long courseId) {
		return transactionRepository.findAllTransactionsOfCourse(courseId);
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	@Override
	public Course findCourseById(Long courseId) {
		return courseRepository.find(courseId);
	}
}
