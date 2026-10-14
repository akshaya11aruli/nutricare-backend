package com.nutricare.ai.repository;

import com.nutricare.ai.entity.WellnessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WellnessProfileRepository extends JpaRepository<WellnessProfile, Long> {
    Optional<WellnessProfile> findByUserId(Long userId);
}
