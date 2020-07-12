package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public int addNote(Note note) {
        return noteMapper.addNote(new Note(null, note.getNoteTitle(), note.getNoteDescription(), note.getUserid()));
    }

    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    public List<Note> getNotes(String username) {

        return noteMapper.getNotes(userMapper.getUser(username).getUserid());
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }
}
