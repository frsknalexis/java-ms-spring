package com.dev.app.mslogmanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.app.mslogmanagement.entity.Log;
import com.dev.app.mslogmanagement.entity.Summary;
import com.dev.app.mslogmanagement.repository.LogRepository;
import com.dev.app.mslogmanagement.repository.SummaryRepository;
import com.dev.app.mslogmanagement.service.LogService;

@Transactional
@Service("logService")
public class LogServiceImpl implements LogService {

	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	@Autowired
	@Qualifier("summaryRepository")
	private SummaryRepository summaryRepository;

	@Override
	public Log saveOrUpdate(Log log) {
		Summary existSummary = summaryRepository.findByCourseId(log.getCourseId()).orElse(null);
		if (existSummary != null) {
			summaryRepository.delete(existSummary);
			existSummary.setHitCount(existSummary.getHitCount() + 1);
			summaryRepository.save(existSummary);
		} else {
			Summary summary = new Summary();
			summary.setCourseId(log.getCourseId());
			summary.setHitCount(1L);
			summaryRepository.save(summary);
		}
		log.setId(UUID.randomUUID());
		Log logReturn = logRepository.save(log);
		return logReturn;
	}

	@Override
	public List<Summary> findPopularCourses() {
		List<Summary> popularCourses = summaryRepository.findPopularCourses();
		return popularCourses;
	}

	@Override
	public Summary findSummaryByCourseId(Long courseId) {
		Summary summary = summaryRepository.findByCourseId(courseId).orElse(null);
		return summary;
	}
}
