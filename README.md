#MobX for Kotlin sample

## About
This is a very simple sample that demonstrates capabilities of [MobX for Kotlin](https://github.com/SamakaCD/mobx).
Disclaimer: I am not an author of this library.

## Core concepts of MobX

### Observable
Observable is a mutable value that notifies about its changes.
```kotlin
var appleCount by observable(0)
var bananaCount by observable(0)
```

### Computed
Computed is a read-only value that is automatically kept in sync with other observables and computed.
Just like an observable, computed value notifies about its changes.
```kotlin
val fruitCount by computed { appleCount + bananaCount }
```

### Autorun
Autorun runs a reaction when its dependencies (observables and computed) are changed.
```kotlin
val disposable = autorun {
   Log.d("FRUITS", "Apples: ${appleCount}, bananas: ${bananaCount}, fruits: ${fruitCount}")
}
```
In this example logging runs immediately and then on any change of appleCount, bananaCount or fruitCount. There are no needs to subscribe to this observables manually! Another example of reaction is UI updating.

### Action
Actions allow to mutate observables. Actions batch mutations, so a notifications will occur only after an action has finished.
```kotlin
fun reset() = action("reset") {
   appleCount = 0
   bananaCount = 0
}
```
A string argument of action is a payload. It can be used for logging.

## Sample

* [FruitCounter](https://github.com/aartikov/MobX-Samples/blob/master/fruitcounter/src/main/java/me/aartikov/fruitcounter/domain/FruitCounter.kt) is a domain object with observable state and actions.
* [FruitCounterViewModel](https://github.com/aartikov/MobX-Samples/blob/master/fruitcounter/src/main/java/me/aartikov/fruitcounter/presentation/FruitCounterViewModel.kt) converts domain state to UI state and calls actions of FruitCounter.
* [localized_string](https://github.com/aartikov/MobX-Samples/tree/master/fruitcounter/src/main/java/me/aartikov/fruitcounter/presentation/utils/localized_string) is a wrapper on top of string resources. It allows to make formatting in ViewModels rather than in acitivities.
* [FruitCounterActivity](https://github.com/aartikov/MobX-Samples/blob/master/fruitcounter/src/main/java/me/aartikov/fruitcounter/presentation/FruitCounterActivity.kt) observes FruitViewModel with a helper method [observe](https://github.com/aartikov/MobX-Samples/blob/master/fruitcounter/src/main/java/me/aartikov/fruitcounter/presentation/utils/mobx/Observe.kt). This method works as `autorun` but manages subscriptions automatically according to a lifecycle.
* Action logging is configured in [FruitCounterApplication](https://github.com/aartikov/MobX-Samples/blob/master/fruitcounter/src/main/java/me/aartikov/fruitcounter/FruitCounterApplication.kt).


![screenshot](https://github.com/aartikov/MobX-Samples/blob/master/fruitcounter/screenshot.png)