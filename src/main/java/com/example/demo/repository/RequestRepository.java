package com.example.demo.repository;

import com.example.demo.entity.ApplicationRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByHandledFalse();
    List<ApplicationRequest> findByHandledTrue();
}
