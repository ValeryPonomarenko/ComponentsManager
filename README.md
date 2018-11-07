# ComponentsManager
![VERSION](https://api.bintray.com/packages/valeryponomarenko/ComponentsManager/Core/images/download.svg)

## Benefits
- No need to save the components somewhere
- No need to remove the component when the Activity/Fragment is going to be destroyed
- The components will be saved while the rotation changes

## Getting started
This library is available on jcenter

If you are using **AndroidX**

```gradle
implementation "com.github.valeryponomarenko.componentsmanager:androidx:LATEST_VERSION"
```
If you are using **AppCompat**

```gradle
implementation "com.github.valeryponomarenko.componentsmanager:appcompat:LATEST_VERSION"
```

## Idea
The idea of the library is to save dagger components and return them when they are needed.
Every component is saved in the static store and removed when the owner is going to be destroyed.

## How to use
The following example will be for the **AndroidX**. If you want to use this library for the **AppCompat** packages, just change **XInjectionManager** to **CompatInjectionManager**.

First thing first, add the lifecycle callbacks listeners. At this step the library registers the lifecycle listener for the future activities and the fragments so the components that are bound to the activity or fragment will be destroyed right after the destruction of the owner.

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        XInjectionManager.init(this)
    }
}
```

For example, the `FirstFragment` (also it works for the activities too) has a component, so you must implement the `IHasComponent` interface and call the `bindComponent` method of the `XInjectionManager` class. When the component is bound, it is available for other classes, but make sure, that these classes will not live longer than the owner of the component.

```kotlin
class FirstFragment : Fragment(), IHasComponent {
    //code...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.bindComponent<FirstFeatureComponent>(this).inject(this)
    }

    override fun getComponent(): FirstFeatureComponent =
        DaggerFirstFeatureComponent.builder()
            .build()
}
```

If the fragment doesn’t have its own component and uses the `AppComponent` to inject the dependencies, just call the findComponent method and specify the class of the component and that is all.

```kotlin
class SecondFragment : Fragment() {
    //code...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.findComponent<AppComponent>().inject(this)
    }
}
```

Also, this method might be used for getting dagger dependencies to build some components.

```kotlin
class AnotherFragment : Fragment(), IHasComponent {
    //code...
    override fun getComponent(): AnotherFeatureComponent =
        DaggerAnotherFeatureComponent.builder()
            .appDependency(XInjectionManager.findComponent())
            .build()
}
```

That's all. There is no need to write code that will save, search or remove components anymore.

For more information, please, read the [wiki pages](https://github.com/ValeryPonomarenko/ComponentsManager/wiki).

## Credits
[Lifecycle aware Dagger components - ProAndroidDev](https://proandroiddev.com/lifecycle-aware-dagger-components-8c74d01fa15)

If you have any questions, feel free to ask me on [LinkedIn](https://www.linkedin.com/in/ponomarenkovalery/).
