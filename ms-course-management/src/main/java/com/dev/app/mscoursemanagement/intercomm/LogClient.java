package com.dev.app.mscoursemanagement.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("log-service")
public interface LogClient {

	@RequestMapping(method = RequestMethod.GET, value = "/service/popular", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Long> findPopularCourses();
}
