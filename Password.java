package jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Password {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;
	
	@OneToOne(fetch= FetchType.LAZY, mappedBy= "password")
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	

	protected Password() {
	}

	public Password(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Password[%s]", number);
	}
}