package com.george.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "county")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "county_name")
    private String name;
    @Column(name = "county_code")
    private String countyCode;
}
