package ru.sonya.week3

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var catList: List<FunCat>
    private lateinit var catAdapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        catList = mutableListOf<FunCat>()

        val manager = LinearLayoutManager(this)

        var catImage: ImageView;


        val catsTitles = mutableListOf<String>("Смешной кот", "Хихитбл кот", "Жесть смешной кот", "Котик хихи", "Забавный кот")
        val catsSubtitles = mutableListOf<String>("хихихи", "хахахха", "АХАХ", "вахзпхв", "апхзвыхща")
        val catsImages = mutableListOf<String>(
            "https://i.pinimg.com/564x/3c/e6/83/3ce6837f24627680bdd499bc8a2fb891.jpg",
            "https://i.pinimg.com/originals/af/ac/e8/aface8ae6a0c7f3d7c23f81e2ba16059.jpg",
            "https://i.pinimg.com/564x/e4/df/ca/e4dfca50c2dd6b4b1e59028676cc3cc6.jpg",
            "https://i.pinimg.com/564x/36/88/a8/3688a852aad9a868dc7aa422b5e90bee.jpg",
            "https://i.pinimg.com/564x/a2/5e/b2/a25eb22ecd9b58d8dc7326f6f93b36e4.jpg"
        )


        catList = catGenerator(100, catsTitles, catsSubtitles, catsImages)

        catAdapter = CatAdapter(Glide.with(this))
        catAdapter.catList = catList
        recyclerView.layoutManager = manager
        recyclerView.adapter = catAdapter

        catAdapter.onItemClick = {
            startActivity(AboutOneCat.createIntent(this, it))
        }
    }

    fun catGenerator(count:Int, catsTitles: MutableList<String>, catsSubtitles: MutableList<String>, catsImages: MutableList<String>): MutableList<FunCat> {
        val generatedList = mutableListOf<FunCat>()
        for (i in 1..count) {
            generatedList.add( FunCat(
                catsImages[(0..(catsImages.size-1)).random()],
                catsTitles[(0..(catsTitles.size-1)).random()],
                catsSubtitles[(0..(catsSubtitles.size-1)).random()])
            )
        }
        return generatedList
    }

}