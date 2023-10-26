package ru.sonya.week3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var catList : List<FunCats>
    private  lateinit var catAdapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        catList = mutableListOf<FunCats>()

        val manager = LinearLayoutManager(this)

        val helpList = listOf<FunCats>(
            (FunCats(R.drawable.cowboy, "Кот-ковбой", "Посмотрите какая у него деловая шляпа!")),
            (FunCats(R.drawable.cutie , "Обаяшка", "Ну какая очаровашка! У неё сегодня день спа, посмотрели и не мешаем")),
            (FunCats(R.drawable.pelmeni , "Кот с пельменями", "Приятного аппетита, кот!")),
            (FunCats(R.drawable.hinata , "Хината", "Аккуратнее с ним, ребята")),
            (FunCats(R.drawable.blem, "Очень смешной кот", "Ха-ха он такой типа блем"))
        )

        catList = buildList<FunCats> { addAll(helpList) }

        catAdapter = CatAdapter()
        catAdapter.catList = catList
        recyclerView.layoutManager = manager
        recyclerView.adapter = catAdapter

        catAdapter.onItemClick = {
            startActivity(AboutOneCat.createIntent(this, it))
        }
    }

}