package ru.sonya.week3.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import ru.sonya.week3.AboutOneCat
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.R
import ru.sonya.week3.viewModel.MainViewModel
import ru.sonya.week3.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var catList: List<FunCat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)

        val manager = LinearLayoutManager(this)

        val itemAdapter = ItemAdapter<CatItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        viewModel.onEvent(LoadEvent())

        viewModel.cats.observe(this) { cats ->
            catList = cats.cats
            itemAdapter.add(catList.map { CatItem(it.title, it.subtitle, it.image) })
        }

        recyclerView.setAdapter(fastAdapter)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)


        fastAdapter.onClickListener = { view, adapter, item, position ->
            startActivity(AboutOneCat.createIntent(this, catList[position]))
            false
        }

    }


}