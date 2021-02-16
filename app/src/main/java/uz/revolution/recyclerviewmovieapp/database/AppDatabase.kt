package uz.revolution.recyclerviewmovieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.revolution.recyclerviewmovieapp.daos.MyDao
import uz.revolution.recyclerviewmovieapp.models.MyMovie

@Database(entities = [MyMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMyDao():MyDao?

    companion object{

        @Volatile
        private var database:AppDatabase?=null

        fun initDatabase(context: Context) {
            synchronized(this){
                if (database==null)
                    database=Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"myMoviea.db")
                        .allowMainThreadQueries()
                        .build()
            }
        }
    }

    object getData{
        fun getDatabase():AppDatabase {
            return database!!
        }
    }

}