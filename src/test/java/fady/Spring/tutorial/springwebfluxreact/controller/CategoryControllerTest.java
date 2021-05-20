package fady.Spring.tutorial.springwebfluxreact.controller;

import fady.Spring.tutorial.springwebfluxreact.domain.Category;
import fady.Spring.tutorial.springwebfluxreact.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CategoryControllerTest {

    WebTestClient webClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;


    @BeforeEach
    void setUp() {

        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController =new CategoryController(categoryRepository);
        webClient= WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void list() {
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(
                        Category.builder().description("cat1").build(),
                        Category.builder().description("cat2").build()
                ));
        webClient.get()
                .uri("api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);

    }

    @Test
    void getById() {
        BDDMockito.given(categoryRepository.findById("someId"))
                .willReturn(Mono.just(
                        Category.builder().description("cat1").build()
                ));


        webClient.get()
                .uri("api/v1/category/someId")
                .exchange()
                .expectBodyList(Category.class);

    }

    @Test
    void createCategory() {
        BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(
                        Category.builder().build()
                ));
        Mono<Category>categoryMono=Mono.just(Category.builder().description("cat1").build());

        webClient.post()
                .uri("api/v1/categories")
                .body(categoryMono,Category.class)
                .exchange()
                .expectStatus()
                .isCreated();





    }

    @Test
    void update() {
        BDDMockito.given(categoryRepository.save(any(Category.class)))
                .willReturn(Mono.just(
                        Category.builder().build()
                ));
        Mono<Category>categoryMono=Mono.just(Category.builder().description("cat1").build());

        webClient.put()
                .uri("api/v1/category/someId")
                .body(categoryMono,Category.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
}