package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM Notes WHERE noteid = #{noteId}")
    Note getNote(Integer noteId);

    @Insert("INSERT INTO Notes(notetitle, notedescription, userid) VALUES (#{noteTitle}, " +
            "${noteDescription}," +
            " #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    Integer addNote(Note note);

    @Delete("DELETE FROM Notes WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);
}
