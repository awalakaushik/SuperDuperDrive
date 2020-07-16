package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;
    private HashService hashService;

    public CredentialController(CredentialService credentialService, UserService userService, HashService hashService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.hashService = hashService;
    }

    @PostMapping(value = {"/credentials"})
    public String addOrUpdateCredentials(Authentication authentication, Credential credentials, Model model) {

        String errorMessage;
        int rowsAffected;

        try {
            if (credentials.getCredentialid() != null) {
                rowsAffected = credentialService.updateCredentials(credentials);
            } else {
                credentials.setUserid(userService.getUser(authentication.getName()).getUserid());
                rowsAffected = credentialService.addCredential(credentials);
            }

            if (rowsAffected <= 0) {
                errorMessage = "There was an error adding/updating the credentials. Please try again!";
                model.addAttribute("errorMessage", errorMessage);
                return "result";
            }

            return "result";
        } catch (Exception ex) {
            errorMessage = "An error occurred while processing the request. Please try again!";
            model.addAttribute("errorMessage", errorMessage);
            return "result";
        }
    }

    @GetMapping("/credentials/delete/{credentialid}")
    public String deleteCredentials(@PathVariable Integer credentialid) {
        credentialService.deleteCredential(credentialid);
        return "result";
    }

}
