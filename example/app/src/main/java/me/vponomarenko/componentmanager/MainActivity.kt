package me.vponomarenko.componentmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import me.vponomarenko.feature.a.FragmentA
import me.vponomarenko.feature.b.FragmentB
import me.vponomarenko.feature.c.FragmentC

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_feature_a -> {
                    openFragment(FragmentA())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_feature_b -> {
                    openFragment(FragmentB())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_feature_c -> {
                    openFragment(FragmentC())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            openFragment(FragmentA())
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
