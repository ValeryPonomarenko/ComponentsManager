# ComponentsManager
![VERSION](https://api.bintray.com/packages/valeryponomarenko/ComponentsManager/Core/images/download.svg)

## Benefits
- No need to save the components somewhere
- No need to remove the component when the Activity/Fragment is going to be destroyed
- The components will be saved while the rotation changes

## Getting started
This library is avaliable on jcenter

If you are using **AndroidX**
```
implementation "com.github.valeryponomarenko.componentsmanager:androidx:LATEST_VERSION"
```
If you are using **AppCompat**
```
COMING SOON
```

## Idea
The idea of the app is to save dagger components and return them when they are needed.
Every component is saved in the static store and removed when the owner is going to be destroyed.

## How to use
Implement the `IHasComponent` interface in the Application class. The method `createComponent` should return the dagger component. Also, you can override the method `getComponentKey` but it isn't important in this step.
```
class App : Application(), IHasComponent {
    ...
    override fun createComponent(): AppComponent = DaggerAppComponent.builder().build()
}
```
Then bind this component to the owner. After the component was bound, the library returns the component, so you can do whatever you want with the component.

Also, at this step the library registers the lifecycle listener for the future activities and the fragments, so the components will be destroyed when it is required.
```
class App : Application(), IHasComponent {

    override fun onCreate() {
        super.onCreate()
        XInjectionManager.instance
            .bindComponent<AppComponent>(this)
            .inject(this)
    }

    override fun createComponent(): AppComponent = DaggerAppComponent.builder().build()
}
```
If your class doesn't have a component and only needs some dependency from the AppComponent, you just call the `findComponent<AppComponent>` method and the library returns the needed component.
```
class FragmentChildB : Fragment() {

    @Inject
    @field:TextHolderForFeatureB
    lateinit var featureBTextHolder: TextHolder

    @Inject
    lateinit var singletonTextHolder: TextHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.instance.findComponent<FeatureBComponent>().inject(this)
    }
}
```
If your class has a component you must do the same that you did with the Application class.
Firstly, implement the `IHasComponent` interface then the method `createComponent` should return the dagger component and finally bind the component to the owner.
```
class FragmentA : Fragment(), IHasComponent {

    @Inject
    lateinit var textHolder: TextHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.instance
            .bindComponent<FeatureAComponent>(this)
            .inject(this)
    }

    override fun createComponent(): FeatureAComponent = 
        DaggerFeatureAComponent.builder()
            .appDependencies(XInjectionManager.instance.findComponent())
            .build()
}
```
Also, if some component needs a dependency, you can find it with method `findComponent`.

That's all. There is no need to write code that will save, search or remove components anymore.