package com.furkanpatat.repository;

import com.furkanpatat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
//    @Query(value = "from User where username =:username")
    Optional<User> findByUsername(String username);
}
