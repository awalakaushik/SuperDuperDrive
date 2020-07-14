package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * from Credentials where credentialid = #{credentialid}")
    Credential selectCredential(Integer credentialid);

    @Select("Select * from Credentials where userid = #{userid}")
    List<Credential> getCredentials(Integer userid);

    @Insert("INSERT INTO Credentials (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, " +
            "#{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer addCredentials(Credential credentials);

    @Update("Update Credentials SET url = #{url}, username = #{username}, password = #{password}, " +
            "where credentialid = #{credentialid}")
    Integer updateCredentials(Credential credentials);

    @Delete("DELETE FROM Credentials WHERE credentialid = #{credentialid}")
    void deleteCredential(Integer credentialid);
}
