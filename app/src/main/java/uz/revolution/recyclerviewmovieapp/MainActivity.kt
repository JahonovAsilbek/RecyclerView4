package uz.revolution.recyclerviewmovieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import uz.revolution.recyclerviewmovieapp.adapters.MyAdapter
import uz.revolution.recyclerviewmovieapp.daos.MyDao
import uz.revolution.recyclerviewmovieapp.database.AppDatabase
import uz.revolution.recyclerviewmovieapp.models.MyMovie

class MainActivity : AppCompatActivity() {

    var listMovie:ArrayList<MyMovie>?=null
    lateinit var adapter: MyAdapter
    private var myDao:MyDao?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database=AppDatabase.getData.getDatabase()
        myDao=database.getMyDao()
        loadData()


        plus_btn.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }


        adapter = MyAdapter()
        adapter.setListMovie(listMovie!!)
//        adapter.notifyDataSetChanged()
        rv.adapter=adapter

        adapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {

            override fun onEditClick(myMovie: MyMovie, position: Int) {
                val bundle = Bundle()
                bundle.putInt("position", position)
                bundle.putInt("id", myMovie.id)

                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
//                onBackPressed()
            }

            override fun onDeleteClick(myMovie: MyMovie, position: Int) {
                myDao?.deleteMovie(myMovie)
                listMovie?.remove(myMovie)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, listMovie!!.size)
                Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
            }

            override fun onItemClick(myMovie: MyMovie) {
                val bundle = Bundle()
                val name: String = myMovie.name
                val author: String = myMovie.author
                val about: String = myMovie.about
                val date: String = myMovie.date
                bundle.putString("name", name)
                bundle.putString("author", author)
                bundle.putString("about", about)
                bundle.putString("date", date)

                val intent = Intent(this@MainActivity, InfoActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
//                onBackPressed()
            }
        })



    }

    override fun onResume() {
        super.onResume()
        val database=AppDatabase.getData.getDatabase()
        myDao=database.getMyDao()


        loadData()
        adapter.setListMovie(listMovie!!)
        adapter.notifyDataSetChanged()


    }

    private fun loadData() {
        listMovie = ArrayList()
        listMovie=myDao?.getAllMovie() as ArrayList
    }

}