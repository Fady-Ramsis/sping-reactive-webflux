package fady.Spring.tutorial.springwebfluxreact.bootstrap;

import fady.Spring.tutorial.springwebfluxreact.domain.Category;
import fady.Spring.tutorial.springwebfluxreact.domain.Vendor;
import fady.Spring.tutorial.springwebfluxreact.repository.CategoryRepository;
import fady.Spring.tutorial.springwebfluxreact.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count().block()==0)
        System.out.println("Data Loader");

        categoryRepository.save(Category.builder()
                .description("fruits").build()).block();
        categoryRepository.save(Category.builder()
                .description("nuts").build()).block();
        categoryRepository.save(Category.builder()
                .description("mango").build()).block();
        categoryRepository.save(Category.builder()
                .description("orange").build()).block();

        System.out.println("Category loading :"+categoryRepository.count().block());

        vendorRepository.save(Vendor.builder().firstName("fady").lastName("ramsis")
                .build()).block();
        vendorRepository.save(Vendor.builder().firstName("ano").lastName("ashraf")
                .build()).block();
        vendorRepository.save(Vendor.builder().firstName("mina").lastName("ashraf")
                .build()).block();


    }
}
