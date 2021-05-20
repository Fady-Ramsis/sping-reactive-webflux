package fady.Spring.tutorial.springwebfluxreact.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {

    @Id
    private String id;


    private String firstName;
    private String lastName;



}
