package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public boolean isFileNameAlreadyPresent(String filename) { return fileMapper.getFile(filename) != null; }

    public int addFile(MultipartFile file, Integer userid) throws IOException {
        return fileMapper.insertFile(new File(null, file.getOriginalFilename(), file.getContentType(),
                Long.toString(file.getSize()),
                userid,file.getBytes()));
    }

    public List<File> getFiles(String username) {
        var files = fileMapper.getFiles(userMapper.getUser(username).getUserid());
        files.forEach(file -> file.setEncodedUrl("data" +
                ":" + file.getContenttype() + ";base64," + Base64.getEncoder().encodeToString(file.getFiledata())));
        return files;
    }

    public File getFile(String filename) {
        return fileMapper.getFile(filename);
    }

    public void deleteFile(Integer fileid) {
        fileMapper.deleteFile(fileid);
    }
}
