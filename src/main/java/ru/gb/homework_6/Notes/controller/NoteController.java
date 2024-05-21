package ru.gb.homework_6.Notes.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework_6.Notes.model.Note;
import ru.gb.homework_6.Notes.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    //    Получить все заметки
    @GetMapping("/show_all")
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    //    Создать заметку
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    //    Получить заметку по id
    @GetMapping("/get/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //    Обновить заметку
    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNoteById(@PathVariable Long id, @RequestBody Note note) {
        try {
            Note updatedNote = noteService.updateNoteById(id, note);
            return ResponseEntity.ok(updatedNote);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //    Удалить заметку
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.ok().build();
    }

}
