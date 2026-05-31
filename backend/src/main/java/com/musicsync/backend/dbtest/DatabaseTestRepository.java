package com.musicsync.backend.dbtest;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DatabaseTestRepository extends JpaRepository<DatabaseTest, Long> {
}

