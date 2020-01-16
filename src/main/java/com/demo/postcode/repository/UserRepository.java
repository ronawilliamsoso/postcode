package com.demo.postcode.repository;

import com.demo.postcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User, Integer >{ }
