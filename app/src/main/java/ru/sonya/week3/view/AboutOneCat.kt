package ru.sonya.week3.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import ru.sonya.week3.viewModel.FunCat
import ru.sonya.week3.R

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



        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        toolbar?.getNavigationIcon()?.setTint(getColor(R.color.white));
        setSupportActionBar(toolbar)
        toolbar.addBackButton {
            finish()
        }

        if (cat != null) {
            val textView2: TextView = findViewById(R.id.OneSubtitle)
            val imageView: ImageView = findViewById(R.id.OneImage)

            toolbar?.title = cat.title
            textView2.text = cat.subtitle

            Glide
                .with(this)
                .load(cat.image)
                .into(imageView)
        }

    }


}