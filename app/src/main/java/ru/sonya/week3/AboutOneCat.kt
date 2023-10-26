package ru.sonya.week3

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class AboutOneCat : AppCompatActivity() {

    companion object CAT : Intent() {

        const val cat: String="cat"
        fun putString(): Intent {
           this.putExtra(cat, this)
           return  this
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val cat = intent.getParcelableExtra<FunCats>("cat")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_one_cat)

        val toolbar: androidx.appcompat.widget.Toolbar? = findViewById(R.id.toolbar2)

        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbar?.navigationIcon?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_IN)
            } else {
                setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            }
        }

        if (cat !=null) {
            val textView2: TextView = findViewById(R.id.OneSubtitle)
            val imageView: ImageView = findViewById(R.id.OneImage)

            toolbar?.title = cat.title
            textView2.text = cat.subtitle
            imageView.setImageResource(cat.image)
        }

    }

}