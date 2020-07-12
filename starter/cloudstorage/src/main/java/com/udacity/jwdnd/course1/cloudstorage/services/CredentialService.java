package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
    }

    public int addCredential(Credential credentials) {
        return credentialMapper.addCredentials(new Credential(null, credentials.getUrl(),
                credentials.getUsername(), credentials.getKey(), credentials.getPassword(), credentials.getUserid()));
    }

    public int updateCredentials(Credential credentials) {
        return credentialMapper.updateCredentials(credentials);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }
}
