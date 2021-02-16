package uz.revolution.recyclerviewmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*
import uz.revolution.recyclerviewmovieapp.R
import uz.revolution.recyclerviewmovieapp.models.MyMovie

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var listMovie: ArrayList<MyMovie>? = null

    fun setListMovie(listMovie: ArrayList<MyMovie>) {
        this.listMovie=listMovie
    }



    private var onItemClickListener:OnItemClickListener?=null

    inner class MyViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView){

        fun onBind(myMovie: MyMovie, position: Int) {
            itemView.name.text=myMovie.name
            itemView.author.text=myMovie.author
            itemView.date.text=myMovie.date

            itemView.delete.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener?.onDeleteClick(myMovie, position )
                    listMovie?.remove(myMovie)
                }
            }

            itemView.edit.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener?.onEditClick(myMovie,position)
                }
            }

            itemView.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener?.onItemClick(myMovie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.onBind(listMovie!![position], position)
    }

    override fun getItemCount(): Int {
        return listMovie!!.size
    }

    interface OnItemClickListener{
        fun onEditClick(myMovie: MyMovie,position: Int)
        fun onDeleteClick(myMovie: MyMovie,position: Int)
        fun onItemClick(myMovie: MyMovie)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener=onItemClickListener
    }
}