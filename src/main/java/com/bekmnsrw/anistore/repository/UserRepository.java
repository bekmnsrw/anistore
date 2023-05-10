package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
