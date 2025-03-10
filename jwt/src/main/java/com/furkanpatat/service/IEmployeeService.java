package com.furkanpatat.service;

import com.furkanpatat.dto.DtoEmployee;

public interface IEmployeeService {
    DtoEmployee findEmployeeById(Long id);
}
