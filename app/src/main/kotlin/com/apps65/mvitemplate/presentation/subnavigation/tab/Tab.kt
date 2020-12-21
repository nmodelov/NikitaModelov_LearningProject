package com.apps65.mvitemplate.presentation.subnavigation.tab

import androidx.annotation.MenuRes
import com.apps65.mvitemplate.R
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Пример класса с тегами табов.
 */
enum class Tab(private val menuItemId: Int, private val screen: FragmentScreen) {
    Tab1(R.id.tab1, tab1Screen()),
    Tab2(R.id.tab2, tab2Screen()),
    Tab3(R.id.tab3, tab3Screen());

    fun menuItemId() = menuItemId
    fun screen(): FragmentScreen = screen
    fun tag() = screen.screenKey

    companion object {
        fun fromMenuItemId(@MenuRes menuItemId: Int): Tab {
            for (tab in values()) {
                if (menuItemId == tab.menuItemId) {
                    return tab
                }
            }
            throw IllegalStateException("Can't find tab with menuItemId = $menuItemId")
        }
    }
}
