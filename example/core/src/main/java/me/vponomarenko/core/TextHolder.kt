package me.vponomarenko.core

import java.util.Date
import kotlin.properties.Delegates

/**
 * Author: Valery Ponomarenko
 * Date: 24/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class TextHolder {
    private val observers = mutableListOf<(String) -> Unit>()

    var text by Delegates.observable("Created at ${Date()}") { _, _, new ->
        observers.forEach { it(new) }
    }

    fun subscribe(listener: (String) -> Unit) {
        listener(text)
        observers.add(listener)
    }

    fun unsubscribe(listener: (String) -> Unit) {
        observers.remove(listener)
    }
}