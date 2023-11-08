package ru.sonya.week3.neverUsed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import ru.sonya.week3.R
import ru.sonya.week3.model.FunCat

//не нужОн больше получается :(
class catAdapter(): AbstractItem<catAdapter.CatViewHolder>()
{
       var catList = listOf<FunCat>()
        set(value) {
            field = value
        }

    var onItemClick: ((FunCat) -> Unit)? = null

    inner class CatViewHolder(itemView: View) :  FastAdapter.ViewHolder<catAdapter>(itemView){

        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView1: TextView = itemView.findViewById(R.id.textView1)
        private val textView2: TextView = itemView.findViewById(R.id.textView2)

        override fun bindView(item: catAdapter, payloads: List<Any>) {
            val cat = catList[position]
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

        override fun unbindView(item: catAdapter) {
            this.itemView.setOnClickListener(null)
        }
    }

    override val layoutRes: Int
    get() = R.layout.activity_main
    override val type: Int
        get() = R.id.recyclerView

    override fun getViewHolder(v: View): CatViewHolder {
        return CatViewHolder(v)
    }
}