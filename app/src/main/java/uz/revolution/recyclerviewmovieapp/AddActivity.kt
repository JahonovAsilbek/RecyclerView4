package uz.revolution.recyclerviewmovieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add.*
import uz.revolution.recyclerviewmovieapp.database.AppDatabase
import uz.revolution.recyclerviewmovieapp.models.MyMovie

class AddActivity : AppCompatActivity() {

    private var listMovie: ArrayList<MyMovie>? = null
    lateinit var database: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        database = AppDatabase.getData.getDatabase()
        val myDao = database.getMyDao()

        save_btn.setOnClickListener {


            if (add_name.text.toString().trim().isNotEmpty()
                && add_author.text.toString().trim().isNotEmpty()
                && add_about.text.toString().trim().isNotEmpty()
                && add_date.text.toString().trim().isNotEmpty())
                {
                    val name = add_name.text
                    val author = add_author.text
                    val about = add_about.text
                    val date = add_date.text


            val myMovie =
                MyMovie(name.toString(), author.toString(), about.toString(), date.toString())

                myDao?.insertMovie(myMovie)
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                onBackPressed()
            } else {
                Toast.makeText(this, "Please, enter the all details", Toast.LENGTH_SHORT).show()
            }

        }
    }
}