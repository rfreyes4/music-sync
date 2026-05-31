package com.musicsync.backend.dbtest;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dbtest")
public class DatabaseTestController {
    
    private final DatabaseTestRepository databaseTestRepository;

    public DatabaseTestController(DatabaseTestRepository databaseTestRepository) {
        this.databaseTestRepository = databaseTestRepository;
    }


    @PostMapping
    public DatabaseTest createTest(){
        DatabaseTest test = new DatabaseTest("Conexión correcta con PostgreSQL");
        return databaseTestRepository.save(test);
    }

    @GetMapping
    public List<DatabaseTest> getTest(){
        return databaseTestRepository.findAll();
    }


}
