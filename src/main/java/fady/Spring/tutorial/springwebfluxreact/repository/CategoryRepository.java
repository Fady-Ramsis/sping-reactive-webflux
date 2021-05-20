package fady.Spring.tutorial.springwebfluxreact.repository;

import fady.Spring.tutorial.springwebfluxreact.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category,String> {
}
