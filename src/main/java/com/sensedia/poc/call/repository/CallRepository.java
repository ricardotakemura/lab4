package com.sensedia.poc.call.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sensedia.poc.call.model.Call;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
}
