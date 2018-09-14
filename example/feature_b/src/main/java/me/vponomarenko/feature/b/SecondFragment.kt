package me.vponomarenko.feature.b

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second.*
import me.vponomarenko.core.SimpleTextWatcher
import me.vponomarenko.core.TextHolder
import me.vponomarenko.feature.b.di.DaggerSecondFeatureComponent
import me.vponomarenko.feature.b.di.SecondFeatureComponent
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.InjectionManager
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class SecondFragment : Fragment(), IHasComponent {

    @Inject
    lateinit var singletonTextHolder: TextHolder

    @Inject
    lateinit var someClass: SomeClass

    private val editTextWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            if (s.isEmpty()) return
            singletonTextHolder.text = s.toString()
        }
    }

    private val textHolderObserver = { changedText: String ->
        label_singleton.text = changedText
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_second, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectionManager.instance
            .bindComponent<SecondFeatureComponent>(this)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText_singleton.addTextChangedListener(editTextWatcher)
        textView_second_feature_deps.text = someClass.toString()
    }

    override fun onResume() {
        super.onResume()
        singletonTextHolder.subscribe(textHolderObserver)
    }

    override fun onPause() {
        super.onPause()
        singletonTextHolder.unsubscribe(textHolderObserver)
    }

    override fun getComponent(): SecondFeatureComponent =
        DaggerSecondFeatureComponent.builder()
            .appDependency(InjectionManager.instance.findComponent())
            .build()
}