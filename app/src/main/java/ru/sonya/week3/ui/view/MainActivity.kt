package ru.sonya.week3.ui.view

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import ru.sonya.week3.R
import ru.sonya.week3.ui.viewModel.DtoCat
import ru.sonya.week3.ui.viewModel.MainEvent
import ru.sonya.week3.ui.viewModel.MainUIEvent
import ru.sonya.week3.ui.viewModel.MainViewModel
import ru.sonya.week3.ui.viewModel.mapToView
import android.util.Log
import ru.sonya.week3.ui.viewModel.SingleLiveEvent

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        val tag = "MainActivity"
        val manager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter<ItemCat>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        recyclerView.adapter = fastAdapter
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)

        viewModel.onEvent(MainUIEvent.LoadEvent)

        viewModel.cats.observe(this) { cats ->
            itemAdapter.add(cats.cats.map(DtoCat::mapToView))
        }

        viewModel.screenEvent.observe(this) {
            when (it) {
                is MainEvent.OpenDetails -> startActivity(AboutOneCat.createIntent(this, it.cat))
                is MainEvent.StartLoading -> {
                    recyclerView.isVisible = false
                    progressBar.isVisible = true
                }
                is MainEvent.DoneLoading -> {
                    progressBar.isVisible = false
                    recyclerView.isVisible = true
                }
                is MainEvent.FailureLoading -> Log.w(tag, "Cat-Loading is failured.")
                else -> {}
            }
        }

        fastAdapter.onClickListener = { _, _, item, _ ->
            viewModel.onEvent(MainUIEvent.OnItemClick(item.mapToDomain()))
            false
        }

    }

}