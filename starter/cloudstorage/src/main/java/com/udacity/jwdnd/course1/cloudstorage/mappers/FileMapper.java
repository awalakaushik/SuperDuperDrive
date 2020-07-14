package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM Files WHERE fileid = #{fileid}")
    File getFile(Integer fileId);

    @Insert("INSERT INTO Files(filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, " +
            "${contentType}," +
            " #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    Integer insertFile(File file);

    @Update("UPDATE Files SET filename = #{filename}, contenttype= #{contentType}, filesize = #{fileSize}, filedata =" +
            " #{fileData}")
    Integer updateFile(File file);

    @Delete("DELETE FROM Files WHERE fileid = #{fileid}")
    void deleteFile(Integer fileId);
}
