package com.spring.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_table")
public class User {

	@Id
	private String userId;

	private String firstName;

	private String lastName;

	private String gender;

	private String emailId;

	private String phoneNumber;

	private String password;

	private Date dateOfBirth;

	private String profilePhoto;

	private LocalDate createDate;

	private LocalDate updateDate;
}
