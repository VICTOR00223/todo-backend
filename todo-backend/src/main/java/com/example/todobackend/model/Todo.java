package com.example.todobackend.model;

import jakarta.persistence.*; // @GeneratedValue, @Id, @Entity
import java.time.LocalDateTime; // LocalDateTime.now()
import lombok.*; // @Getter, @Setter, @NoArgsConstructor

@Entity // Tells JPA this class maps to a database table
@Table(name = "todos") // Defines the table name
@Getter // Creates getter method
@Setter // Creates setter method
@NoArgsConstructor // Needed for JPA to load  entities
public class Todo
{
    @Id // This field is the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generate automatically
    private Long id;

    // 1. Added nullable = false to ensure titles aren't empty in the DB
    @Column(nullable = false)
    private String title;

    private String description;

    // 2. Boolean defaults can be tricky; this ensures it's never null
    @Column(nullable = false)
    private boolean completed;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist //Before inserting this entity into the database, run this method.
    protected void onCreate()
    {
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }
}
