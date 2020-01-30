package house.info.houseinfo.repository;

import house.info.houseinfo.model.House;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface HouseRepository extends MongoRepository<House, BigInteger> {

    House findByPostCode(String postCode);
    House findByID(BigInteger ID);

}
