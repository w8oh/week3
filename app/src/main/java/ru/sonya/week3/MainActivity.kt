package ru.sonya.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var catList : ArrayList<FunCats>
    private  lateinit var catAdapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        catList = ArrayList()
        val manager = LinearLayoutManager(this)
        catList.add(FunCats(R.drawable.cowboy, "Кот-ковбой", "Посмотрите какая у него деловая шляпа!"))
        catList.add(FunCats(R.drawable.cutie , "Обаяшка", "Ну какая очаровашка! У неё сегодня день спа, посмотрели и не мешаем"))
        catList.add(FunCats(R.drawable.pelmeni , "Кот с пельменями", "Приятного аппетита, кот!"))
        catList.add(FunCats(R.drawable.hinata , "Хината", "Аккуратнее с ним, ребята"))
        catList.add(FunCats(R.drawable.blem, "Очень смешной кот", "Ха-ха он такой типа блем"))

        catAdapter = CatAdapter(catList)
        recyclerView.layoutManager = manager
        recyclerView.adapter = catAdapter
    }
}