package fady.Spring.tutorial.springwebfluxreact.controller;


import fady.Spring.tutorial.springwebfluxreact.domain.Category;
import fady.Spring.tutorial.springwebfluxreact.repository.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;

@RestController
public class CategoryController {

    private  final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/v1/categories")
    Flux<Category>list()
    {
        return categoryRepository.findAll();
    }

    @GetMapping("/api/v1/category/{id}")
    Mono<Category>getById(@PathVariable String id){
        return categoryRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/api/v1/categories")
    Mono<Void>create(@RequestBody Publisher<Category> categoryPublisher){
        return categoryRepository.saveAll(categoryPublisher).then();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/v1/category/{id}")
    Mono<Category>update(@PathVariable String id,@RequestBody Category category){
        category.setId(id);
        return categoryRepository.save(category);
    }



}
