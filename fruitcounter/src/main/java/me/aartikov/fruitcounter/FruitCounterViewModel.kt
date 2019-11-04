package me.aartikov.fruitcounter

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ivansadovyi.mobx.*

class FruitCounterViewModel : ViewModel() {

    companion object {
        private const val MAX_APPLE_COUNT = 10
        private const val MAX_BANANA_COUNT = 10
    }

    var appleCount by observable(0)
    var bananaCount by observable(0)

    val decrementApplesEnabled by computed { appleCount > 0 }
    val incrementApplesEnabled by computed { appleCount < MAX_APPLE_COUNT }

    val decrementBananasEnabled by computed { bananaCount > 0 }
    val incrementBananasEnabled by computed { bananaCount < MAX_BANANA_COUNT }

    val fruitCount by computed { appleCount + bananaCount }
    val resetEnabled by computed { fruitCount > 0 }


    private val loggingDisposable: Disposable

    init {
        loggingDisposable = autorun {
            Log.d("FRUITS", "Apples: $appleCount, bananas: $bananaCount, fruits: $fruitCount")
        }
    }

    fun onDecrementApplesClicked() {
        appleCount--
    }

    fun onIncrementApplesClicked() {
        appleCount++
    }

    fun onDecrementBananasClicked() {
        bananaCount--
    }

    fun onIncrementBananasClicked() {
        bananaCount++
    }

    fun onResetClicked() = action {
        appleCount = 0
        bananaCount = 0
    }

    override fun onCleared() {
        super.onCleared()
        loggingDisposable.dispose()
    }
}
