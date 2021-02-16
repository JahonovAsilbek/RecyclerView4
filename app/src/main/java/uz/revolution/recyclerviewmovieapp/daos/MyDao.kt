package uz.revolution.recyclerviewmovieapp.daos

import androidx.room.*
import uz.revolution.recyclerviewmovieapp.models.MyMovie

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(myMovie: MyMovie)

    @Query("SELECT * FROM movie")
    fun getAllMovie(): List<MyMovie>

    @Query("UPDATE movie SET name =:name, author=:author, about=:about, date=:date WHERE id=:id")
    fun updateMovie(name: String, author: String, about: String, date: String, id: Int)

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getDataByID(id: Int):MyMovie

    @Delete
    fun deleteMovie(myMovie: MyMovie)

}
