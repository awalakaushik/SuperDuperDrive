package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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
    private UserService userService;

    public HomeController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
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
    public String getNotes(Authentication authentication, Model model) {

        model.addAttribute("notes", noteService.getNotes(authentication.getName()));

        return "home";
    }

    @PostMapping("/note/add")
    public String addNote(Authentication authentication, Note note, Model model) {

        String errorMessage;

        note.setUserId(userService.getUser(authentication.getName()).getUserid());

        int rowsAdded = noteService.addNote(note);

        if (rowsAdded < 0) {
            errorMessage = "There was an error adding the note. Please try again!";

            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("notes", noteService.getNotes(authentication.getName()));

            return "result";
        }

        model.addAttribute("notes", noteService.getNotes(authentication.getName()));

        note.setNoteTitle("");
        note.setNoteDescription("");

        return "result";
    }

    @PostMapping("/note/edit")
    public String editNote(Authentication authentication, Note note, Model model) {

        String errorMessage;

        int rowsUpdated = noteService.updateNote(note);

        if (rowsUpdated < 0) {
            errorMessage = "There was an error updating the note. Please try again!";

            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("notes", noteService.getNotes(authentication.getName()));

            return "result";
        }

        model.addAttribute("notes", noteService.getNotes(authentication.getName()));

        note.setNoteTitle("");
        note.setNoteDescription("");

        return "result";
    }

    @DeleteMapping("/note/delete")
    public String deleteNote(Authentication authentication, Note note, Model model) {

        noteService.deleteNote(note.getNoteId());

        model.addAttribute("notes", noteService.getNotes(authentication.getName()));

        return "result";
    }

}
