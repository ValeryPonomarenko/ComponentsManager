package me.vponomarenko.feature.b

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_child_feature_b.*
import me.vponomarenko.core.TextHolder

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FragmentChildB : Fragment() {

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

    override fun onResume() {
        super.onResume()
        TextHolder.instance.subscribe(textHolderWatcherForSingleton)
    }

    override fun onPause() {
        super.onPause()
        TextHolder.instance.unsubscribe(textHolderWatcherForSingleton)
    }
}