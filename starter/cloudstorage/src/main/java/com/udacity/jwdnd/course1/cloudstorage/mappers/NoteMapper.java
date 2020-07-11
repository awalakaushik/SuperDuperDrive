package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM Notes WHERE noteid = #{noteId}")
    Note getNote(Integer noteId);

    @Select("Select * from Notes where username = #{username}")
    Note[] getNotes(String username);

    @Insert("INSERT INTO Notes(notetitle, notedescription, userid) VALUES (#{noteTitle}, " +
            "${noteDescription}," +
            " #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    Integer addNote(Note note);

    @Update("UPDATE Notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteid = #{noteId}" +
            " and userid = #{userId}")
    Integer updateNote(Note note);

    @Delete("DELETE FROM Notes WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);
}
