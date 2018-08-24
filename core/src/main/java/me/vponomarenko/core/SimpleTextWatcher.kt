package me.vponomarenko.core

import android.text.Editable
import android.text.TextWatcher

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

abstract class SimpleTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {}
}