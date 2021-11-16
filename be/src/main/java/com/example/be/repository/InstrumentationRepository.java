package com.example.be.repository;

import com.example.be.entity.Instrumentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InstrumentationRepository extends JpaRepository<Instrumentation, Integer> {
    @Query("select i from Instrumentation i where i.isObservable = 1")
    List<Instrumentation> getInstrumentations();
}
