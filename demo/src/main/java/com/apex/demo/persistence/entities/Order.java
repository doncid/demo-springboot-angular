package com.apex.demo.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Order {
    @Id
    @GeneratedValue
    Long id;

    @NotBlank(message = "First name can not be null or empty.")
    @Column(name = "first_name")
    String firstName;

    @NotBlank(message = "Last name can not be null or empty.")
    @Column(name = "last_name")
    String lastName;

    String grade;

    String address1;
    String address2;
    String city;
    String state;
    String zip;
}
