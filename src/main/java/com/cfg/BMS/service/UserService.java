package com.cfg.BMS.service;

import com.cfg.BMS.dto.TheaterDto;
import com.cfg.BMS.dto.UserDto;
import com.cfg.BMS.exception.ResourceNotFoundException;
import com.cfg.BMS.model.Theater;
import com.cfg.BMS.model.User;
import com.cfg.BMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found with id " + id));
        return mapToDto(user);
    }
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long id,UserDto userDto){
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found with id " + id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return mapToDto(user);
    }
    public void deleteUser(Long id) {
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found with id " + id));
        userRepository.delete(user);
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());

        return user;
    }

}
