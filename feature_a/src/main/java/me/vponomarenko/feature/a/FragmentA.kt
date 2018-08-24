package me.vponomarenko.feature.a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_feature_a.*
import me.vponomarenko.core.TextHolder

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FragmentA : Fragment() {

    private val textHolderWatcher = { changedText: String ->
        text.text = changedText
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_feature_a, container, false)

    override fun onResume() {
        super.onResume()
        TextHolder.instance.subscribe(textHolderWatcher)
    }

    override fun onPause() {
        super.onPause()
        TextHolder.instance.unsubscribe(textHolderWatcher)
    }
}