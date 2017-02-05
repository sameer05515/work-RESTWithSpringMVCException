package com.jcombat.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcombat.bean.Employee;
import com.jcombat.bean.ErrorResponse;
import com.jcombat.exception.EmployeeException;

@RestController
public class EmployeeController {

	/*
	 * http://localhost:8080/RESTWithSpringMVCException/Ramesh?empId=1234
	 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
	 */
	@RequestMapping(value = "/{firstName}", method = RequestMethod.GET)
	public ResponseEntity<Employee> showMessage(
			@PathVariable("firstName") String firstName,
			@RequestParam(value = "empId", required = false, defaultValue = "00000") final String empId) throws EmployeeException {

		Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setFirstName(firstName);

		if (StringUtils.isNumeric(firstName)) {
			throw new EmployeeException("Invalid employee name requested");
		}

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}