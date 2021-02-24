package uz.revolution.recyclerviewmovieapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_edit.*
import uz.revolution.recyclerviewmovieapp.daos.MyDao
import uz.revolution.recyclerviewmovieapp.database.AppDatabase

class EditActivity : AppCompatActivity() {

    private var name: String? = null
    private var author: String? = null
    private var about: String? = null
    private var date: String? = null
    private var position: Int = 0
    private var id: Int = 0
    private var myDao: MyDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setSupportActionBar(edit_movie_title)

        val database = AppDatabase.getData.getDatabase()
        myDao = database.getMyDao()

        getData()

        val myMovie = myDao?.getDataByID(id)

        edit_name.setText(myMovie?.name)
        edit_author.setText(myMovie?.author)
        edit_about.setText(myMovie?.about)
        edit_date.setText(myMovie?.date)

        edit_btn.setOnClickListener {

            if (edit_name.text.toString().trim().isNotEmpty()
                && edit_author.text.toString().trim().isNotEmpty()
                && edit_about.text.toString().trim().isNotEmpty()
                && edit_date.text.toString().trim().isNotEmpty()
            ) {

                myDao?.updateMovie(
                    edit_name.text.toString(),
                    edit_author.text.toString(),
                    edit_about.text.toString(),
                    edit_date.text.toString(),
                    id
                )
//
//           val intent = Intent(this, MainActivity::class.java)
//           intent.putExtra("id", id)
//           intent.putExtra("position", position)
//           startActivity(intent)
                onBackPressed()
                Toast.makeText(this, "Edited", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please, enter the all details", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getData() {


        val bundle = intent.extras
        if (bundle != null) {
            position = bundle.getInt("position", 0)
            id = bundle.getInt("id", 0)
        }
    }
}