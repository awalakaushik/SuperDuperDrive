package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }

//    TODO: Add fetch files when we first login
    @GetMapping()
    public String getHomePage() {
        return "home";
    }

    /*
    * Notes
    * */
    @GetMapping("/notes")
    public String getNotes(Authentication authentication, Note note, Model model) {
        model.addAttribute("notes", noteService.getNotes(authentication.getName()));
        return "home";
    }

    @PostMapping("/note/add")
    public String addNote(Authentication authentication, Note note, Model model) {
        noteService.addNote(note);
        model.addAttribute("notes", noteService.getNotes(authentication.getName()));
        note.setNoteTitle("");
        note.setNoteDescription("");
        return "home";
    }

    @DeleteMapping("/note/delete")
    public String deleteNote(Authentication authentication, Note note, Model model) {
        noteService.deleteNote(note.getNoteId());
        model.addAttribute("notes", noteService.getNotes(authentication.getName()));
        return "home";
    }

}
