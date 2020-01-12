package me.aartikov.fruitcounter.domain

import com.ivansadovyi.mobx.action
import com.ivansadovyi.mobx.computed
import com.ivansadovyi.mobx.observable

class FruitCounter {

    companion object {
        private const val MAX_APPLE_COUNT = 10
        private const val MAX_BANANA_COUNT = 10
    }

    var appleCount by observable(0)
        private set

    var bananaCount by observable(0)
        private set

    val fruitCount by computed { appleCount + bananaCount }

    val canDecrementApples by computed { appleCount > 0 }
    val canIncrementApples by computed { appleCount < MAX_APPLE_COUNT }
    val canDecrementBananas by computed { bananaCount > 0 }
    val canIncrementBananas by computed { bananaCount < MAX_BANANA_COUNT }

    fun decrementApples() = action("decrementApples") {
        if (canDecrementApples) {
            appleCount--
        }
    }

    fun incrementApples() = action("incrementApples") {
        if (canIncrementApples) {
            appleCount++
        }
    }

    fun decrementBananas() = action("decrementBananas") {
        if (canDecrementBananas) {
            bananaCount--
        }
    }

    fun incrementBananas() = action("incrementBananas") {
        if (canIncrementBananas) {
            bananaCount++
        }
    }

    fun reset() = action("reset") {
        appleCount = 0
        bananaCount = 0
    }

    override fun toString(): String {
        return "FruitCounter(appleCount = $appleCount, bananaCount = $bananaCount, fruitCount = $fruitCount)"
    }

}