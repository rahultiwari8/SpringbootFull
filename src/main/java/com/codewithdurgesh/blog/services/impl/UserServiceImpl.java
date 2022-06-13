package com.codewithdurgesh.blog.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepository;
import com.codewithdurgesh.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createuser(UserDto userdto) {
		User user = this.userDtotoUser(userdto);
		User user1 = this.userRepository.save(user);
		return this.userToUserDto(user1);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));
		user.setName(userdto.getName());
		user.setAbout(userdto.getAbout());
		user.setPassword(userdto.getPassword());
		user.setEmail(userdto.getEmail());

		User updateduser = this.userRepository.save(user);

		UserDto updateDto = userToUserDto(updateduser);

		return updateDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));

		UserDto dto = userToUserDto(user);

		return dto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allusers = userRepository.findAll();

		List<UserDto> allUsersInDto = allusers.stream().map(usr -> userToUserDto(usr)).collect(Collectors.toList());
		return allUsersInDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));
		
		this.userRepository.delete(user);

	}

	public User userDtotoUser(UserDto dto) {
		User user = new User();
		user.setName(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setAbout(dto.getAbout());

		return user;
	}

	public UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());

		return userDto;
	}

}
