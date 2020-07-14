package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM Files WHERE filename = #{filename}")
    File getFile(String filename);

    @Select("Select * from Files where userid = #{userid}")
    List<File> getFiles(Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, " +
            "#{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insertFile(File file);

    @Delete("DELETE FROM Files WHERE fileid = #{fileid}")
    void deleteFile(Integer fileid);
}
