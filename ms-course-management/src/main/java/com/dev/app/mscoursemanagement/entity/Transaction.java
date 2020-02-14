package com.dev.app.mscoursemanagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dev.app.mscoursemanagement.model.IModel;

import lombok.Data;

@Data
@Entity
@Table(name = "transactions", schema = "public")
public class Transaction implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8715365480033949438L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "date_of_issue")
	private LocalDateTime dateOfIssue;
}
