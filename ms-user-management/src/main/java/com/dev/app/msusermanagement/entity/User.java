package com.dev.app.msusermanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dev.app.msusermanagement.enums.Role;

import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -661498484798827165L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "role")
	private Role role;
}
