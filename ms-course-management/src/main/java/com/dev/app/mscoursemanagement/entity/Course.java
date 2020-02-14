package com.dev.app.mscoursemanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dev.app.mscoursemanagement.model.IModel;

import lombok.Data;

@Data
@Entity
@Table(name = "courses", schema = "public")
public class Course implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4295964088803235343L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "publish_date")
	private LocalDate publishDate;
}
