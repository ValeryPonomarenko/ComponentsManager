package me.vponomarenko.injectionmanager.x

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.callbacks.IRemoveComponentCallback

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

internal class XFragmentLifecycleHelper(
    private val removeComponentCallback: IRemoveComponentCallback
) : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        if (f !is IHasComponent<*>) return

        if (f.requireActivity().isFinishing) {
            removeComponentCallback.onRemove(f.getComponentKey())
            return
        }

        if (f.isStateSaved) {
            return
        }

        var anyParentIsRemoving = false
        var parent = f.parentFragment
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving && !parent.isStateSaved
            parent = parent.parentFragment
        }
        if (f.isRemoving || anyParentIsRemoving) {
            removeComponentCallback.onRemove(f.getComponentKey())
        }
    }
}