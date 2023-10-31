package ru.sonya.week3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class AboutOneCat : AppCompatActivity() {

    companion object {

        private const val CAT = "cat"
        fun createIntent(context: Context, cat: FunCat): Intent {
            return Intent(context, AboutOneCat::class.java).apply {
                putExtra(CAT, cat)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val cat = intent.getParcelableExtra<FunCat>(CAT)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_one_cat)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar2)

        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbar?.getNavigationIcon()?.setTint(getColor(R.color.white));

        if (cat !=null) {
            val textView2: TextView = findViewById(R.id.OneSubtitle)
            val imageView: ImageView = findViewById(R.id.OneImage)

            toolbar?.title = cat.title
            textView2.text = cat.subtitle

            Glide
                .with(this)
                .load(cat.image)
                .into(imageView)
            //imageView.setImageResource(cat.image)
        }

    }

}