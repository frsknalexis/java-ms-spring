package com.dev.app.mslogmanagement.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.mslogmanagement.entity.Log;
import com.dev.app.mslogmanagement.entity.Summary;
import com.dev.app.mslogmanagement.service.LogService;

@RestController
public class LogRestController {

	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	@PostMapping("/service/create")
	ResponseEntity<?> saveLog(@RequestBody Log log) {
		log.setLogDate(LocalDateTime.now());
		Log logSaved = logService.saveOrUpdate(log);
		return new ResponseEntity<>(logSaved, HttpStatus.CREATED);
	}
	
	@GetMapping("/service/popular")
	ResponseEntity<?> findPopularCourses() {
		List<Long> idList = new ArrayList<Long>();
		List<Summary> popularCourses = logService.findPopularCourses();
		if (popularCourses != null) {
			idList = popularCourses.parallelStream()
									.map(s -> {
										return s.getCourseId();
									}).collect(Collectors.toList());
		}
		return new ResponseEntity<>(idList, HttpStatus.OK);
	}
	
	@PostMapping("/service/summary")
	ResponseEntity<?> getSummaryOfCourse(@RequestBody Long courseId) {
		Summary summary = logService.findSummaryByCourseId(courseId);
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}
