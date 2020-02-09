package com.dev.app.mslogmanagement.service;

import java.util.List;

import com.dev.app.mslogmanagement.entity.Log;
import com.dev.app.mslogmanagement.entity.Summary;

public interface LogService {

	Log saveOrUpdate(Log log);
	
	List<Summary> findPopularCourses();
	
	Summary findSummaryByCourseId(Long courseId);
}
