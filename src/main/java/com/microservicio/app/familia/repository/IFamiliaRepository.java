package com.microservicio.app.familia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.app.familia.entity.Personas;

@Repository
public interface IFamiliaRepository extends MongoRepository<Personas, Long>{

}
