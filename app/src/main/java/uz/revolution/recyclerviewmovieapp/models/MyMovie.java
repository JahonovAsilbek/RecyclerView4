package uz.revolution.recyclerviewmovieapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class MyMovie {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id = 0;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "author")
    String author;

    @ColumnInfo(name = "about")
    String about;

    @ColumnInfo(name = "date")
    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Ignore
    public MyMovie(int id, String name, String author, String about, String date) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.about = about;
        this.date = date;
    }

    @Ignore
    public MyMovie(String name, String author, String about, String date) {
        this.name = name;
        this.author = author;
        this.about = about;
        this.date = date;
    }

    public MyMovie() {
    }
}
