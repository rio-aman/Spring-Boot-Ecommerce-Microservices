package com.ecommerce.user.model;

//import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

// these are commented because now no jpa used and postgesql database instead
// using mongodb database

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
//@Entity(name = "user_table") // this also used when jpa there
@Document(collection = "users")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  // used when jpa there
    private String id;
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String email;
    private String phone;
    private UserRole role = UserRole.CUSTOMER;
    private Address address;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
