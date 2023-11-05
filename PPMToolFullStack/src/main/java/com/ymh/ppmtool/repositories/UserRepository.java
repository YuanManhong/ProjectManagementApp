package com.ymh.ppmtool.repositories;

import com.ymh.ppmtool.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
