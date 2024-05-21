package ru.gb.homework_6.Notes.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.homework_6.Notes.model.Note;
import ru.gb.homework_6.Notes.repository.NoteRepository;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note updateNoteById(Long id, Note noteDetails) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(noteDetails.getTitle());
            note.setBody(noteDetails.getBody());
            return noteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("Note not found, id " + id));
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

}
