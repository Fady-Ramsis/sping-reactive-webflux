package fady.Spring.tutorial.springwebfluxreact.controller;

import fady.Spring.tutorial.springwebfluxreact.domain.Category;
import fady.Spring.tutorial.springwebfluxreact.domain.Vendor;
import fady.Spring.tutorial.springwebfluxreact.repository.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/vendor")
    Mono<Void>create(@RequestBody Publisher<Vendor>vendorPublisher){
        return vendorRepository.saveAll(vendorPublisher).then();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/v1/vendor/{id}")
    Mono<Vendor>update(@PathVariable String id,@RequestBody Vendor vendor){
        vendor.setId(id);
        return vendorRepository.save(vendor);

    }



}
