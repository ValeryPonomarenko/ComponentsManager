package me.vponomarenko.feature.a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_feature_a.*
import me.vponomarenko.core.TextHolder
import me.vponomarenko.feature.a.di.FeatureAComponent
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FragmentA : Fragment(), IHasComponent {

    @Inject
    lateinit var textHolder: TextHolder

    private val textHolderWatcher = { changedText: String ->
        text.text = changedText
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_feature_a, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.instance
            .bindComponent<FeatureAComponent>(this)
            .inject(this)
    }

    override fun onResume() {
        super.onResume()
        textHolder.subscribe(textHolderWatcher)
    }

    override fun onPause() {
        super.onPause()
        textHolder.unsubscribe(textHolderWatcher)
    }

    override fun createComponent() = FeatureAComponent.Initializer.init()
}