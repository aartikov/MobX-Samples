package me.aartikov.fruitcounter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.activity_fruit_counter.*
import me.aartikov.fruitcounter.R
import me.aartikov.fruitcounter.presentation.utils.localized_string.localizedText
import me.aartikov.fruitcounter.presentation.utils.mobx.observe

class FruitCounterActivity : AppCompatActivity() {

    private lateinit var viewModel: FruitCounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_counter)

        viewModel = ViewModelProviders.of(this, FruitCounterViewModelFactory()).get()

        decrementApplesButton.setOnClickListener { viewModel.onDecrementApplesClicked() }
        incrementApplesButton.setOnClickListener { viewModel.onIncrementApplesClicked() }
        decrementBananasButton.setOnClickListener { viewModel.onDecrementBananasClicked() }
        incrementBananasButton.setOnClickListener { viewModel.onIncrementBananasClicked() }
        resetButton.setOnClickListener { viewModel.onResetClicked() }

        // Divided to several blocks to show how to make granular updates of views
        observe {
            appleCountTextView.localizedText = viewModel.formattedAppleCount
            decrementApplesButton.isEnabled = viewModel.decrementApplesButtonEnabled
            incrementApplesButton.isEnabled = viewModel.incrementApplesButtonEnabled
        }

        observe {
            bananaCountTextView.localizedText = viewModel.formattedBananaCount
            decrementBananasButton.isEnabled = viewModel.decrementBananasButtonEnabled
            incrementBananasButton.isEnabled = viewModel.incrementBananasButtonEnabled
        }

        observe {
            fruitCountTextView.localizedText = viewModel.formattedFruitCount
            resetButton.isEnabled = viewModel.resetButtonEnabled
        }
    }
}
