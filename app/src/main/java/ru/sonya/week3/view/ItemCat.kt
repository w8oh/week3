package ru.sonya.week3.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import ru.sonya.week3.R

open class ItemCat(
    var title: String? = null,
    var subtitle: String? = null,
    var image: String? = null
): AbstractItem<ItemCat.ViewHolder>() {


    override val type: Int
        get() = R.id.recyclerView

    override val layoutRes: Int
        get() = R.layout.each_item

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<ItemCat>(view) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView1: TextView = itemView.findViewById(R.id.textView1)
        private val textView2: TextView = itemView.findViewById(R.id.textView2)
        override fun bindView(item: ItemCat, payloads: List<Any>) {
            textView1.text = item.title
            textView2.text = item.subtitle

            Glide
                .with(itemView.context)
                .load(item.image)
                .dontAnimate()
                .into(imageView)
        }

        override fun unbindView(item: ItemCat) {
            this.itemView.setOnClickListener(null)
        }
    }
}