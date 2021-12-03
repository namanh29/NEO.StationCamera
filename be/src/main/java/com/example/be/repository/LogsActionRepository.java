package com.example.be.repository;

import com.example.be.entity.LogsAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsActionRepository extends JpaRepository<LogsAction, Integer> {

    @Query("select max(l.insParamId) from LogsAction l")
    Integer getMaxId();
}
