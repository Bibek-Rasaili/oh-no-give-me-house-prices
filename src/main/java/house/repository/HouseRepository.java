package house.repository;

import house.model.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.UUID;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {

    House findByID(String ID);
}
