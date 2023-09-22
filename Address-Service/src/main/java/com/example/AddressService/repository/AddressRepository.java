package com.example.AddressService.repository;

import com.example.AddressService.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(
            nativeQuery = true,
            value
                    = "SELECT a.id, a.city, a.state " +
                    "FROM mytables.address_ser a " +
                    "join mytables.emp_ser e on e.id = a.employee_id " +
                    "where a.employee_id=:employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);

}
