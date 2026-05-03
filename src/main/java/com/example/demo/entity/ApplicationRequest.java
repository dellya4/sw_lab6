package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "ApplicationRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName", length = 200)
    private String userName;

    @Column(name = "phone", length = 200)
    private String phone;

    @Column(name = "handled")
    private boolean handled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Courses course;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Operators> operators;

}
