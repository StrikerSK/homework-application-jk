package com.inverview.application.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_request")
public class UserRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String requestType;

	@Column
	@Min(value = 100000, message = "Minimal limit is 100000")
	@Max(value = 999999, message = "Maximal limit is 999999")
	private Integer policyNumber;

	@Column
	@Pattern(regexp = "^[A-Z][a-zA-Z]+", message = "Characters with starting capital letter are only valid!")
	private String firstName;

	@Column
	@Pattern(regexp = "^[A-Z][a-zA-Z]+", message = "Characters with starting capital letter are only valid!")
	private String lastName;

	@Column
	@Pattern(regexp = "[a-zA-Z0-9 _]{0,5000}", message = "Only 5000 characters available!")
	private String request;

	public UserRequest() {
	}

	public UserRequest(String requestType, Integer policyNumber, String firstName, String lastName, String request) {
		this.requestType = requestType;
		this.policyNumber = policyNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.request = request;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Integer getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Integer policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
}
