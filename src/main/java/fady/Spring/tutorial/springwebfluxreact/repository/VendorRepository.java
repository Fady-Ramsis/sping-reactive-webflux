package fady.Spring.tutorial.springwebfluxreact.repository;

import fady.Spring.tutorial.springwebfluxreact.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor,String> {
}
