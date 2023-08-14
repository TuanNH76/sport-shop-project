package com.sportshoptest.Service;

import com.sportshoptest.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {
    User findOne(String email);
    Collection<User> findByRole(String role);
    User save(User user);
    User update(User user);

    Page<User> findByRole(String role, Pageable pageable);
    void delete(String email);
}
