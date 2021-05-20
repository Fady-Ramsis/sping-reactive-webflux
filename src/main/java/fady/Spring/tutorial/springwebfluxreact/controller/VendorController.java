package fady.Spring.tutorial.springwebfluxreact.controller;

import fady.Spring.tutorial.springwebfluxreact.domain.Category;
import fady.Spring.tutorial.springwebfluxreact.domain.Vendor;
import fady.Spring.tutorial.springwebfluxreact.repository.VendorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {
     private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping("/api/v1/vendors")
    Flux<Vendor>getAllVendors(){
        return  vendorRepository.findAll();
    }
    @GetMapping("/api/v1/vendor/{id}")
    Mono<Vendor> getById(@PathVariable String id){
        return vendorRepository.findById(id);
    }


}
