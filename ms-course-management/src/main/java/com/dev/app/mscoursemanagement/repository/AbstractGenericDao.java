package com.dev.app.mscoursemanagement.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.dev.app.mscoursemanagement.model.IModel;

@Transactional
public class AbstractGenericDao<T extends IModel> implements IGenericDao<T> {

	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = 
			(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	@Override
	public T find(Long id) {
		return em.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		List<T> resultList = em.createQuery("SELECT V FROM " + this.entityClass.getCanonicalName() + " V").getResultList();
		return resultList;
	}

	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.getReference(entityClass, id));
	}

	@Override
	public Session getSession() {
		return (Session) this.em.getDelegate();
	}
}
