package com.apps65.blank

import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.apps65.mvitemplate.R
import com.apps65.mvitemplate.presentation.blank.BlankFragment
import com.kaspersky.kaspresso.screens.KScreen

internal object BlankScreen : KScreen<BlankScreen>() {
    override val layoutId: Int? = R.layout.fragment_blank
    override val viewClass: Class<*>? = BlankFragment::class.java

    val increment = KButton { withId(R.id.blank_button) }
    val counter = KTextView { withId(R.id.counter) }
}
