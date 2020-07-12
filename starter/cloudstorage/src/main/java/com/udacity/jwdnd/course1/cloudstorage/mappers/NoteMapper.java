package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM Notes WHERE noteid = #{noteid}")
    Note getNote(Integer noteid);

    @Select("Select * from Notes where userid = #{userid}")
    List<Note> getNotes(Integer userid);

    @Insert("INSERT INTO Notes(notetitle, notedescription, userid) VALUES (#{noteTitle}, " +
            "#{noteDescription}," +
            " #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Note note);

    @Update("UPDATE Notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteid = #{noteid}")
    int updateNote(Note note);

    @Delete("DELETE FROM Notes WHERE noteid = #{noteid}")
    void deleteNote(Integer noteid);
}
