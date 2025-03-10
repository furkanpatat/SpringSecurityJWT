package com.furkanpatat.controller;

import com.furkanpatat.dto.DtoEmployee;
import jakarta.validation.constraints.NotNull;

public interface IRestEmployeeController {
    DtoEmployee findEmployeeById(@NotNull Long id);
}
