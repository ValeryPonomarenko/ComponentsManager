package me.vponomarenko.feature.b

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_child_feature_b.*
import me.vponomarenko.core.TextHolder
import me.vponomarenko.feature.b.di.FeatureBComponent
import me.vponomarenko.feature.b.di.TextHolderForFeatureB
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FragmentChildB : Fragment() {

    @Inject
    @field:TextHolderForFeatureB
    lateinit var featureBTextHolder: TextHolder

    @Inject
    lateinit var singletonTextHolder: TextHolder

    private val textHolderWatcherForSingleton = { changedText: String ->
        child_text_singleton.text = changedText
    }

    private val textHolderWatcherForFeatureB = { changedText: String ->
        child_text_feature_b.text = changedText
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_child_feature_b, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.instance.findComponent<FeatureBComponent>().inject(this)
    }

    override fun onResume() {
        super.onResume()
        singletonTextHolder.subscribe(textHolderWatcherForSingleton)
        featureBTextHolder.subscribe(textHolderWatcherForFeatureB)
    }

    override fun onPause() {
        super.onPause()
        singletonTextHolder.unsubscribe(textHolderWatcherForSingleton)
        featureBTextHolder.unsubscribe(textHolderWatcherForFeatureB)
    }
}