
package com.nequi.franquicias.repository;

import com.nequi.franquicias.model.Franquicia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends MongoRepository<Franquicia, String> {
}
