package com.project.wineshop.dto.request;

import com.project.wineshop.dto.request.user.UserRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Map;

@Data
public class OrderRequestDto {
    private Boolean createAccount;
    @NotNull
    @Valid
    private UserRequestDto userRequest;
    @NotBlank
    private String payment;
    @NotNull
    private Boolean isGift;
    @NotNull
    @Size(min = 1)
    private Map<Long, Integer> products;
}
