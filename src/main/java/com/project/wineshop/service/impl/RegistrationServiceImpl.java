package com.project.wineshop.service.impl;

import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.model.User;
import com.project.wineshop.service.AddressService;
import com.project.wineshop.service.RegistrationService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.impl.AddressMapper;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public RegistrationServiceImpl(UserService userService,
                                   AddressService addressService,
                                   AddressMapper addressMapper) {
        this.userService = userService;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @Override
    public User register(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        user.setBirthDate(userRegisterDto.getBirthDate());
        user.setAddress(addressService.save(addressMapper
                .mapToModel(userRegisterDto.getAddress())));
        return null;
    }
}
