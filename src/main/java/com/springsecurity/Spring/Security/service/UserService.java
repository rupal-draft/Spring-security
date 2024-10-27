package com.springsecurity.Spring.Security.service;

import com.springsecurity.Spring.Security.dto.AuthDto;
import com.springsecurity.Spring.Security.dto.UserDto;
import com.springsecurity.Spring.Security.entities.UserEntity;
import com.springsecurity.Spring.Security.exception.ResourceNotFound;
import com.springsecurity.Spring.Security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository
                .findByEmail(username)
                .orElseThrow(
                        ()-> new BadCredentialsException("Account not found with this email:"+username)
                );
    }

    public UserDto signup(AuthDto inputUser){
        Optional<UserEntity> user= userRepository.findByEmail(inputUser.getEmail());
        if(user.isPresent())
            throw new BadCredentialsException("User with this email is already present!");

        UserEntity userEntity = modelMapper.map(inputUser,UserEntity.class);
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);

        return modelMapper.map(userRepository.save(userEntity),UserDto.class);
    }

    public UserEntity getUserFromId(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(
                        ()->new ResourceNotFound("No user found with this id:"+userId)
                );
    }

    public UserEntity findByEmail(String email) {
        return  userRepository
                .findByEmail(email)
                .orElse(null);
    }

    public UserEntity saveUser(UserEntity toBeSavedUser) {
        return userRepository.save(toBeSavedUser);
    }
}
