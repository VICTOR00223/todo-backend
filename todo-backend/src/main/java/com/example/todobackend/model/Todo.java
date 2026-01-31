package com.example.todobackend.model;

import jakarta.persistence.*; // @GeneratedValue, @Id, @Entity
import java.time.LocalDateTime; // LocalDateTime.now()
import lombok.*; // @Getter, @Setter, @NoArgsConstructor

@Entity // Tells JPA this class maps to a database table
@Table(name = "todos") // Defines the table name
@Getter // Creates getter method
@Setter // Creates setter method
@NoArgsConstructor // Needed for JPA to load load entities
public class Todo
{
    @Id // This field is the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generate automatically
    private Long id;

    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;

    @PrePersist //Before inserting this entity into the database, run this method.
    protected void onCreate()
    {
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }
}
