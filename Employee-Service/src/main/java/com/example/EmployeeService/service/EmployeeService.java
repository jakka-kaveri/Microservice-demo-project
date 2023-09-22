package com.example.EmployeeService.service;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.repository.EmployeeRepo;
import com.example.EmployeeService.response.AddressResponse;
import com.example.EmployeeService.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    public EmployeeResponse getEmployeeById(int id) {

        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/address-service/address/{id}", AddressResponse.class, id);
        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }

//    public List<Employee> displayAll(){
//        List<Employee> employees= employeeRepo.findAll();
//        return employees;
//    }

//    public EmployeeResponse getEmployeeById(int id) {
//        Optional<Employee> employee = employeeRepo.findById(id);
//        return mapper.map(employee, EmployeeResponse.class);
//    }
}
