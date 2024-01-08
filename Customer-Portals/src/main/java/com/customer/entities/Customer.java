package com.customer.entities;

import java.security.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name ="CustomerDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="date_of_birth")
	private String dob;
	
	@Column(name="occupation")
	private String occupation;
	
	@Column(name="customer_group")
	private String customerGroup;

}
