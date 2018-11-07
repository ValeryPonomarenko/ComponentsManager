package me.vponomarenko.feature.c

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_feature_c.*
import me.vponomarenko.core.TextHolder
import me.vponomarenko.feature.R
import me.vponomarenko.feature.c.di.FeatureCComponent
import me.vponomarenko.feature.c.di.TextHolderForFeatureC
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.customlifecycle.StoredComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 28/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FragmentC : Fragment(), IHasComponent {

    @Inject
    lateinit var singletonTextHolder: TextHolder

    @Inject
    @field:TextHolderForFeatureC
    lateinit var featureCTextHolder: TextHolder

    private var storedComponent: StoredComponent<FeatureCComponent>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_feature_c, container, false)

    override fun onResume() {
        super.onResume()
        storedComponent = XInjectionManager.bindComponentToCustomLifecycle(this)
        storedComponent?.component?.inject(this)
        text_singleton.text = singletonTextHolder.text
        text_feature_c_scope.text = featureCTextHolder.text
    }

    override fun onPause() {
        super.onPause()
        storedComponent?.lifecycle?.destroy()
        storedComponent = null
    }

    override fun getComponent() = FeatureCComponent.Initializer.init()
}