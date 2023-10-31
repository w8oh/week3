package ru.sonya.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class CatAdapter(activity: RequestManager): RecyclerView.Adapter<CatAdapter.CatViewHolder>()
{
    val activity1 = activity
       var catList = listOf<FunCat>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((FunCat) -> Unit)? = null

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView1: TextView = itemView.findViewById(R.id.textView1)
        private val textView2: TextView = itemView.findViewById(R.id.textView2)

        fun bind(position: Int) {
            val cat = catList[position]
            //this.imageView.setImageResource(cat.image)
            this.textView1.text = cat.title
            this.textView2.text = cat.subtitle

            Glide
                .with(itemView.context)
                .load(cat.image)
                .dontAnimate()
                .into(imageView)


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