package com.myblog2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String body;

        @ManyToOne(fetch = FetchType.LAZY) // this is used for load the comment whenever it is needed
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;

        // Hibernate's caching mechanism is a powerful feature designed to improve the performance of data access in Java applications by reducing the number of database hits. It operates at multiple levels, each serving a different purpose and use case.

}
