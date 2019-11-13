package me.aartikov.fruitcounter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.ivansadovyi.mobx.Disposable
import com.ivansadovyi.mobx.autorun

// Life-cycle aware version of autorun
fun LifecycleOwner.observe(body: () -> Unit) {
    if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
        return
    }

    var disposable: Disposable? = null
    if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
        disposable = autorun(body)
    }

    lifecycle.addObserver(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            disposable = autorun(body)
        } else if (event == Lifecycle.Event.ON_STOP) {
            disposable?.dispose()
        }
    })
}