package ru.sonya.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatAdapter (private val catList:ArrayList<FunCats>): RecyclerView.Adapter<CatAdapter.CatViewHolder>()
{

    var onItemClick: ((FunCats) -> Unit)? = null
    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = catList[position]
        holder.imageView.setImageResource(cat.image)
        holder.textView1.text = cat.title
        holder.textView2.text = cat.subtitle

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(cat)
        }
    }
}