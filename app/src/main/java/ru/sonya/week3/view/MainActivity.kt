package ru.sonya.week3.view

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import ru.sonya.week3.R
import ru.sonya.week3.model.DBApp
import ru.sonya.week3.model.FunCat
import ru.sonya.week3.viewModel.MainViewModel
import ru.sonya.week3.viewModel.MainViewModelFactory
import java.util.Date


class MainActivity : AppCompatActivity() {

    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private  var catList: List<FunCat> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var date: Date
        date = Date(System.currentTimeMillis())

        val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("time", date.getTime())
        editor.commit()

        factory = MainViewModelFactory((this.applicationContext as DBApp).db, sharedPreferences)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

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

            itemAdapter.add(catList.map { CatItem(it.title, it.subtitle, it.image) })
            progressBar.isVisible = false
            date = Date(System.currentTimeMillis())
            editor.putLong("old_time", date.getTime())
            editor.commit()
        }

        recyclerView.setAdapter(fastAdapter)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)

        viewModel.screenEvent.observe(this) {
            when (it) {
                is MainEvent.OpenDetails -> startActivity(AboutOneCat.createIntent(this, it.cat))
            }
        }

        fastAdapter.onClickListener = { view, adapter, item, position ->
            viewModel.onEvent(MainUIEvent.OnItemClick(catList!![position]))
            false
        }

    }


}