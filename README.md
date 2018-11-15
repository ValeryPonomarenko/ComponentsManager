# ComponentsManager
![VERSION](https://api.bintray.com/packages/valeryponomarenko/ComponentsManager/Core/images/download.svg)

README for the **1.x.x** of the library lives [here](https://github.com/ValeryPonomarenko/ComponentsManager/blob/master/README_V1.md).

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

## What's new
### 2.0.1
If you use the `*InjectionManager.findComponent()` method and the component was not found, the ComponentNotFoundException will be more informative, beucase the type of the component will be printed.
```kotlin
//before
Caused by: me.vponomarenko.injectionmanager.exeptions.ComponentNotFoundException: Component for the Function1<java.lang.Object, java.lang.Boolean> was not found
...

//after
Caused by: me.vponomarenko.injectionmanager.exeptions.ComponentNotFoundException: Component of the FragmentChildB type was not found
...
```
But if you use the `*InjectionManager.findComponent(predicate)` method, the exception's massage will be the same as it was in 2.0.0.

### 2.0.0
The main difference between the **2.0.0** version and the **1.1.0** version that the **IHasComponent** interface is a generic one. Therefore, you must specify the class of the component.
```kotlin
//before
class MyFragment : Fragment(), IHasComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager
            .bindComponent<MyComponent>(this)
            .inject(this)
    }

    override fun getComponent(): Any = DaggerMyComponent.create()
}

//after
class MyFragment : Fragment(), IHasComponent<MyComponent> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager
            .bindComponent(this)
            .inject(this)
    }

    override fun getComponent(): MyComponent = DaggerMyComponent.create()
}
```

### 1.1.0
Add static methods into the (X/Compat)InjectionManager, so there will no need to get the instance and then call the needed method.
Examples:
```kotlin
//before
fun foo() {
    XInjectionManager.instance.init(this)
    XInjectionManager.instance.bindComponent<AppComponent>(this)
}

//after
fun foo() {
    XInjectionManager.init(this)
    XInjectionManager.bindComponent<AppComponent>(this)
}
```

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
class FirstFragment : Fragment(), IHasComponent<FirstFeatureComponent> {
    //code...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.bindComponent(this).inject(this)
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
class AnotherFragment : Fragment(), IHasComponent<AnotherFeatureComponent> {
    //code...
    override fun getComponent(): AnotherFeatureComponent =
        DaggerAnotherFeatureComponent.builder()
            .appDependency(XInjectionManager.findComponent())
            .build()
}
```

That's all. There is no need to write code that will save, search or remove components anymore.

For more information, please, read the [wiki pages](https://github.com/ValeryPonomarenko/ComponentsManager/wiki).

## Links
[Lifecycle aware Dagger components - ProAndroidDev](https://proandroiddev.com/lifecycle-aware-dagger-components-8c74d01fa15)

If you have any questions, feel free to ask me on [LinkedIn](https://www.linkedin.com/in/ponomarenkovalery/).
