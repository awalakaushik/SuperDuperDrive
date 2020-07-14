package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping(value = {"/files"})
    public String insertOrUpdateFile(Authentication authentication, MultipartFile fileUpload, Model model) throws IOException {

        String errorMessage;
        int rowsAffected;

        try {
            if (fileUpload.isEmpty() || fileService.isFileNameAlreadyPresent(fileUpload.getOriginalFilename())) {

                errorMessage = "Either file doesn't exist or file name already present. Please try again.";
                model.addAttribute("errorMessage", errorMessage);
                return "result";

            } else {
                rowsAffected = fileService.addFile(fileUpload, userService.getUser(authentication.getName()).getUserid());

                if (rowsAffected <= 0) {
                    errorMessage = "There was an error adding/updating the file. Please try again!";
                    model.addAttribute("errorMessage", errorMessage);
                    return "result";
                }
            }

            return "result";
        } catch (Exception ex) {
            errorMessage = "An error occurred while processing the request. Please try again!";
            model.addAttribute("errorMessage", errorMessage);
            return "result";
        }
    }

    @GetMapping("/files/delete/{fileid}")
    public String deleteFile(@PathVariable Integer fileid) {
        fileService.deleteFile(fileid);
        return "result";
    }
}
