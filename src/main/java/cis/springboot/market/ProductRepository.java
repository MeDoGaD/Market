package cis.springboot.market;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Product getById(String id);
    Product getByName(String name);
    void deleteByName(String name);
}
