package com.george.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @OneToOne
    private County county;
    @OneToOne
    private Locality locality;
}
