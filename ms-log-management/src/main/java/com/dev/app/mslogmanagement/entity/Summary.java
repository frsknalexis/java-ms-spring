package com.dev.app.mslogmanagement.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "summary")
public class Summary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6399944785686254309L;

	@PrimaryKeyColumn(name = "course_id", type = PrimaryKeyType.PARTITIONED)
	private Long courseId;
	
	@PrimaryKeyColumn(name = "hit_count", ordinal = 0, type = PrimaryKeyType.CLUSTERED, 
			ordering = Ordering.DESCENDING)
	private Long hitCount;
}
