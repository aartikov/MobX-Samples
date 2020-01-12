package me.aartikov.fruitcounter

import android.app.Application
import android.util.Log
import com.ivansadovyi.mobx.Action

class FruitCounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Action.listen { payload ->
            Log.d("FRUIT_COUNTER", "Action '$payload' is executed")
            Log.d("FRUIT_COUNTER", "---------")
        }
    }
}