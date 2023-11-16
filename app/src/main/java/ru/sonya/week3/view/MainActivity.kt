package ru.sonya.week3.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.R
import ru.sonya.week3.viewModel.MainViewModel
import ru.sonya.week3.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private  var catList: List<FunCat> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)

        val manager = LinearLayoutManager(this)

        val itemAdapter = ItemAdapter<CatItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        viewModel.onEvent(MainUIEvent.LoadEvent)

        viewModel.cats.observe(this) { cats ->

            cats.cats.fold(
                onSuccess = {
                    catList = it.orEmpty()
                },
                onFailure = {
                    Toast.makeText(
                        this,
                        "Error Occurred: ${it.message}",
                        Toast.LENGTH_LONG
                    ).show() })

            itemAdapter.add(catList!!.map { CatItem(it.title, it.subtitle, it.image) })
        }

        viewModel.screenEvent.observe(this) {
            when (it) {
                is MainEvent.OpenDetails -> startActivity(AboutOneCat.createIntent(this, it.cat))
            }
        }

        recyclerView.setAdapter(fastAdapter)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)


        fastAdapter.onClickListener = { view, adapter, item, position ->
            viewModel.onEvent(MainUIEvent.OnItemClick(catList!![position]))
            false
        }

    }


}