package jpa;





import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class jparunner implements CommandLineRunner {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(jparunner.class, args);

	}

	public void run(String... args) throws Exception {
		
		logger.info("course 10001->{}",studentRepository.findbyid(10001L));
		courseRepository.save(new Course("Computer"));
		studentRepository.saveStudentWithPassport();
		
			
		List<Review> reviews = new ArrayList<>();
		courseRepository.addReviewsForCourse(10003L, reviews );	
		studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 Steps")); 
		
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));logger.info("Full Time Employees -> {}", 
					employeeRepository.retrieveAllFullTimeEmployees());
		logger.info("Part Time Employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());


	}

}
