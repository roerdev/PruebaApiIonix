package com.roerdev.pruebaapiionix.services.impl;


import com.roerdev.pruebaapiionix.dto.UserDto;
import com.roerdev.pruebaapiionix.exceptions.NotFoundException;
import com.roerdev.pruebaapiionix.models.UserEntity;
import com.roerdev.pruebaapiionix.repository.UserRepository;
import com.roerdev.pruebaapiionix.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto saveUser(UserDto userDto) throws Exception {
        UserEntity entity = userDto.toEntity();
        var newUser = userRepository.save(entity);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        var user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            throw new NotFoundException("No se encontro registro para el email: " + email);
        }
        return modelMapper.map(user.get(), UserDto.class);
    }

    @Override
    public Page<UserDto> getAllUser(Pageable page) {
        var allUser = userRepository.findAll(page);
        return allUser.map(userEntity -> modelMapper.map(userEntity, UserDto.class));
    }

}
