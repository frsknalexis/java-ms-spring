package com.dev.app.mscoursemanagement.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.app.mscoursemanagement.entity.Transaction;

@Transactional
@Repository("transactionRepository")
public class TransactionRepositoryImpl extends AbstractGenericDao<Transaction> implements TransactionRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> findAllTransactionsOfUser(Long userId) {
		String hql = "SELECT T FROM Transaction T WHERE T.userId = :pUserId";
		Query query = em.createQuery(hql);
		query.setParameter("pUserId", userId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> findAllTransactionsOfCourse(Long courseId) {
		String hql = "SELECT T FROM Transaction T WHERE T.course.id = :pCourseId";
		Query query = em.createQuery(hql);
		query.setParameter("pCourseId", courseId);
		return query.getResultList();
	}	
}
