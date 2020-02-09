package com.dev.app.mslogmanagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;

import lombok.Data;

@Data
@Table(value = "log")
public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2883798324894692114L;
	@PrimaryKey
	@CassandraType(type = Name.UUID)
	private UUID id;
	
	@Column(value = "ip")
	private String ip;
	
	@Column(value = "course_id")
	private Long courseId;
	
	@Column(value = "log_date")
	private LocalDateTime logDate;
}
