package me.aartikov.fruitcounter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.activity_fruit_counter.*

class FruitCounterActivity : AppCompatActivity() {

    private lateinit var viewModel: FruitCounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_counter)
        viewModel = ViewModelProviders.of(this).get()

        decrementApplesButton.setOnClickListener { viewModel.onDecrementApplesClicked() }
        incrementApplesButton.setOnClickListener { viewModel.onIncrementApplesClicked() }
        decrementBananasButton.setOnClickListener { viewModel.onDecrementBananasClicked() }
        incrementBananasButton.setOnClickListener { viewModel.onIncrementBananasClicked() }
        resetButton.setOnClickListener { viewModel.onResetClicked() }

        // Divided to several blocks to show how to make granular updates of views
        observe {
            appleCountTextView.text = getString(R.string.apple_count_template, viewModel.appleCount)
            decrementApplesButton.isEnabled = viewModel.decrementApplesEnabled
            incrementApplesButton.isEnabled = viewModel.incrementApplesEnabled
        }

        observe {
            bananaCountTextView.text = getString(R.string.banana_count_template, viewModel.bananaCount)
            bananaCountTextView.text = getString(R.string.banana_count_template, viewModel.bananaCount)
            decrementBananasButton.isEnabled = viewModel.decrementBananasEnabled
            incrementBananasButton.isEnabled = viewModel.incrementBananasEnabled
        }

        observe {
            fruitCountTextView.text = getString(R.string.fruit_count_template, viewModel.fruitCount)
            resetButton.isEnabled = viewModel.resetEnabled
        }
    }
}
