package com.devsuperior.bds01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAll(Pageable pageable) {

		Page<Employee> page = employeeRepository.findAll(pageable);
		return page.map(employee -> new EmployeeDTO(employee));
	}

	public EmployeeDTO insert(EmployeeDTO dto) {
		Employee employee = new Employee(dto);
		employee.setDepartment(new Department(dto.getDepartmentId(), null));
		employee = employeeRepository.save(employee);
		return new EmployeeDTO(employee);
	}

}
