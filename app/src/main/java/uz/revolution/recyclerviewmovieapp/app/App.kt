package uz.revolution.recyclerviewmovieapp.app

import android.app.Application
import uz.revolution.recyclerviewmovieapp.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.initDatabase(this)
    }
}