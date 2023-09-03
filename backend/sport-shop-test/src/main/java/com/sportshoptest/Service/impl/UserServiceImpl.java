package com.sportshoptest.Service.impl;

import com.sportshoptest.Entity.Cart;
import com.sportshoptest.Entity.User;
import com.sportshoptest.Enums.ResultEnum;
import com.sportshoptest.Exceptions.MyException;
import com.sportshoptest.Repository.CartRepository;
import com.sportshoptest.Repository.UserRepository;
import com.sportshoptest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public User save(User user) {
        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);

            // initial Cart
            Cart savedCart = cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new MyException(ResultEnum.VALID_ERROR);
        }

    }

    @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }

    @Override
    public Page<User> findByRole(String role, Pageable pageable) {
        return userRepository.findAllByRole(role,pageable);
    }

    @Override
    public void delete(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) userRepository.delete(user);
        else throw new MyException(ResultEnum.USER_NOT_FOUND);
    }

}
