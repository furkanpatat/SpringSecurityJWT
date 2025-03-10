package com.furkanpatat.controller.impl;

import com.furkanpatat.controller.IRestEmployeeController;
import com.furkanpatat.dto.DtoEmployee;
import com.furkanpatat.service.IEmployeeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class RestEmployeeControllerImpl implements IRestEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@NotNull @PathVariable("id") Long id) {
        return employeeService.findEmployeeById(id);
    }
}
