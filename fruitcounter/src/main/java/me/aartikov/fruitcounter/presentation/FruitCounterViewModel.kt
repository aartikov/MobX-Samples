package me.aartikov.fruitcounter.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ivansadovyi.mobx.Disposable
import com.ivansadovyi.mobx.autorun
import com.ivansadovyi.mobx.computed
import me.aartikov.fruitcounter.R
import me.aartikov.fruitcounter.domain.FruitCounter
import me.aartikov.fruitcounter.presentation.utils.localized_string.LocalizedString

class FruitCounterViewModel(private val counter: FruitCounter) : ViewModel() {

    val formattedAppleCount by computed { LocalizedString.resource(R.string.apple_count_template, counter.appleCount) }
    val formattedBananaCount by computed { LocalizedString.resource(R.string.banana_count_template, counter.bananaCount) }
    val formattedFruitCount by computed { LocalizedString.resource(R.string.fruit_count_template, counter.fruitCount) }

    val decrementApplesButtonEnabled by computed { counter.canDecrementApples }
    val incrementApplesButtonEnabled by computed { counter.canIncrementApples }
    val decrementBananasButtonEnabled by computed { counter.canDecrementBananas }
    val incrementBananasButtonEnabled by computed { counter.canIncrementBananas }

    val resetButtonEnabled by computed { counter.fruitCount > 0 }

    private val loggingDisposable: Disposable = autorun {
        Log.d("FRUITS", "Apples: ${counter.appleCount}, bananas: ${counter.bananaCount}, fruits: ${counter.fruitCount}")
    }

    fun onDecrementApplesClicked() {
        counter.decrementApples()
    }

    fun onIncrementApplesClicked() {
        counter.incrementApples()
    }

    fun onDecrementBananasClicked() {
        counter.decrementBananas()
    }

    fun onIncrementBananasClicked() {
        counter.incrementBananas()
    }

    fun onResetClicked() {
        counter.reset()
    }

    override fun onCleared() {
        super.onCleared()
        loggingDisposable.dispose()
    }
}
