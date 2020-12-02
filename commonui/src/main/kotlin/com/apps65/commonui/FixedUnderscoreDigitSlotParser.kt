package com.apps65.commonui

import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.slots.Slot

class FixedUnderscoreDigitSlotParser : UnderscoreDigitSlotsParser() {
    override fun slotFromNonUnderscoredChar(character: Char): Slot =
        PredefinedSlots.hardcodedSlot(character).withTags(Slot.TAG_DECORATION)
}
