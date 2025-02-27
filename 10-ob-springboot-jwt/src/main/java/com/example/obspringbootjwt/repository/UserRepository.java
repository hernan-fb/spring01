package com.example.obspringbootjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.obspringbootjwt.domain.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
