package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * from Credentials where credentialid = #{credentialid}")
    Credential selectCredential(Integer credentialid);

    @Insert("INSERT INTO Credentials (url, username, key, password, userId) VALUES (#{url}, #{username}, #{key}, " +
            "#{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer addCredentials(Credential credentials);

    @Update("Update Credentials SET url = #{url}, username = #{username}, key = #{key}, password = #{password}, " +
            "userid = #{userid} where credentialid = #{credentialid}")
    Integer updateCredentials(Credential credentials);

    @Delete("DELETE FROM Credentials WHERE credentialid = #{credentialid}")
    void deleteCredential(Integer credentialid);
}
