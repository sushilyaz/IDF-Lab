package org.example.idflab.repository;

import org.example.idflab.model.CurrencyMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends MongoRepository<CurrencyMongo, String> {
}
