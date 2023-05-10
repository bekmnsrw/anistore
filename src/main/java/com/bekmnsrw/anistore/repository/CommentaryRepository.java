package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
