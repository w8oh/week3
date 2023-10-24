package ru.sonya.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class AboutOneCat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_one_cat)

        val cat = intent.getParcelableExtra<FunCats>("cat")
        if (cat !=null) {
            val textView1: TextView = findViewById(R.id.OneTitle)
            val textView2: TextView = findViewById(R.id.OneSubtitle)
            val imageView: ImageView = findViewById(R.id.OneImage)

            textView1.text = cat.title
            textView2.text = cat.subtitle
            imageView.setImageResource(cat.image)
        }
    }
}