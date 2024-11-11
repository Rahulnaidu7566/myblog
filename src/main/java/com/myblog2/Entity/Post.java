package com.myblog2.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (
        name ="Posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"}) }
)
public class Post {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    //cascde means that we  are post entity as parent child (or) when we save a post entity any new comment entities associated with the post will also be saved to the database automatically
    private List<Comment> comments;
}
