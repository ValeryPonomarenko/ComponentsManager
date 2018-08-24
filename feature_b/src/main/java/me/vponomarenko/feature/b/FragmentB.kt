package me.vponomarenko.feature.b

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_feature_b.*
import me.vponomarenko.core.SimpleTextWatcher
import me.vponomarenko.core.TextHolder

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FragmentB : Fragment() {

    private val editTextWatcherForSingleton = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            if (s.isEmpty()) return
            TextHolder.instance.text = s.toString()
        }
    }

    private val editTextWatcherForFeatureB = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            if (s.isEmpty()) return
//            TextHolder.instance.text = s.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_feature_b, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.childFragmentContainer, FragmentChildB())
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText_singleton.addTextChangedListener(editTextWatcherForSingleton)
        editText_feature_b.addTextChangedListener(editTextWatcherForFeatureB)
    }
}