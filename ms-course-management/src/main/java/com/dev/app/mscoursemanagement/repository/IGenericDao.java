package com.dev.app.mscoursemanagement.repository;

import java.util.List;

import org.hibernate.Session;

import com.dev.app.mscoursemanagement.model.IModel;

public interface IGenericDao<T extends IModel> {

	T find(Long id);
	
	List<T> findAll();
	
	void save(T entity);
	
	T update(T entity);
	
	void delete(Long id);
	
	Session getSession();
}
