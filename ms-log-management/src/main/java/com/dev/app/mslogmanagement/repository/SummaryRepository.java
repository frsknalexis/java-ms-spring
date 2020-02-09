package com.dev.app.mslogmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.app.mslogmanagement.entity.Summary;

@Repository("summaryRepository")
public interface SummaryRepository extends CrudRepository<Summary, Long> {

	Optional<Summary> findByCourseId(Long courseId);
	
	@Query("select * from summary limit 100")
	List<Summary> findPopularCourses();
}
