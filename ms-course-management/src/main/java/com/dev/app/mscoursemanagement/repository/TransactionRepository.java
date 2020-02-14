package com.dev.app.mscoursemanagement.repository;

import java.util.List;

import com.dev.app.mscoursemanagement.entity.Transaction;

public interface TransactionRepository extends IGenericDao<Transaction> {

	List<Transaction> findAllTransactionsOfUser(Long userId);
	
	List<Transaction> findAllTransactionsOfCourse(Long courseId);
}
