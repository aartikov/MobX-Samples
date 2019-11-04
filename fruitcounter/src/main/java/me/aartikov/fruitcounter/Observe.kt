package me.aartikov.fruitcounter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.ivansadovyi.mobx.autorun

// Observes changes of observables and computed values until onStop will be called. Should be called from onStart.
fun LifecycleOwner.observe(body: () -> Unit) {
    val disposable = autorun(body)
    lifecycle.addObserver(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_STOP) {
            disposable.dispose()
        }
    })
}