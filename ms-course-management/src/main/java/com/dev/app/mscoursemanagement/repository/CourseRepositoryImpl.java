package com.dev.app.mscoursemanagement.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.app.mscoursemanagement.entity.Course;

@Transactional
@Repository("courseRepository")
public class CourseRepositoryImpl extends AbstractGenericDao<Course> implements CourseRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> filterCourses(String text) {
		String hql = "SELECT C FROM Course C WHERE 1=1 ";
		if (text != null && !"".equals(text.trim())) {
			hql += "AND (lower(C.title) like lower(:pText) OR lower(C.category) like lower(:pText) OR lower(C.author) " +
						" like lower(:pText))";
		}
		
		Query query = em.createQuery(hql);
		if (text != null && !"".equals(text.trim())) {
			query.setParameter("pText", "%" + text + "%");
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> filterCoursesByIdList(List<Long> idList) {
		String hql = "SELECT C FROM Course C WHERE C.id IN (:pIdList)";
		Query query = em.createQuery(hql);
		query.setParameter("pIdList", idList);
		return query.getResultList();
	}
}
