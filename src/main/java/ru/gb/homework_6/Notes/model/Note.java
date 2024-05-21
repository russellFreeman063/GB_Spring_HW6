package ru.gb.homework_6.Notes.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity(name = "note")
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(name = "date_create")
    private LocalDateTime date;

    @PrePersist
    public void prePersist() {
        date = LocalDateTime.now();
    }
}
