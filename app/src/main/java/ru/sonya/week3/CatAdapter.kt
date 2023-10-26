package ru.sonya.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatAdapter (): RecyclerView.Adapter<CatAdapter.CatViewHolder>()
{
   var  catList:MutableList<FunCats> = mutableListOf()
       set(value) {
           field = value
       }

    var onItemClick: ((FunCats) -> Unit)? = null

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)

        fun bind(position: Int) {
            val cat = catList[position]
            this.imageView.setImageResource(cat.image)
            this.textView1.text = cat.title
            this.textView2.text = cat.subtitle

            this.itemView.setOnClickListener{
                onItemClick?.invoke(cat)
            }
        }

        fun unbind() {
            this.itemView.setOnClickListener(null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onViewRecycled(holder: CatViewHolder) = holder.unbind()
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) = holder.bind(position)
}