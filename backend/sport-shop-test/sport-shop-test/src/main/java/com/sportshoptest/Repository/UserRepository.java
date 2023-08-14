package com.sportshoptest.Repository;

import com.sportshoptest.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    Collection<User> findAllByRole(String role);

    Page<User> findAllByRole(String role, Pageable pageable);
}
