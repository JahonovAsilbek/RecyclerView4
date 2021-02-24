package uz.revolution.recyclerviewmovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)


        val bundle = intent.extras
        if (bundle != null) {

            info_title.title = bundle.getString("name")
            info_name.text = "Movie name: " + bundle.getString("name")
            info_author.text = "Movie authors: " + bundle.getString("author")
            info_about.text = "About movie: " + bundle.getString("about")
            info_date.text = "Date: " + bundle.getString("date")
        }

        setSupportActionBar(info_title)

        info_close.setOnClickListener {
            onBackPressed()
        }
    }
}