package com.dev.app.mscoursemanagement.repository;

import java.util.List;

import com.dev.app.mscoursemanagement.entity.Course;

public interface CourseRepository extends IGenericDao<Course> {

	List<Course> filterCourses(String text);
	
	List<Course> filterCoursesByIdList(List<Long> idList);
}
