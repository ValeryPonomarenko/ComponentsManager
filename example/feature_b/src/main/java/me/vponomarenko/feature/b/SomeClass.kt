package me.vponomarenko.feature.b

import java.util.Date

class SomeClass {
    private val createdAt: String = Date().toString()

    override fun toString(): String = "This is an object that was provided by the SecondFeatureModule\n\nIt was create at $createdAt"
}