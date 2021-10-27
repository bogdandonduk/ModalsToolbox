package bottomsheet.core.anatomy.overflowmenu

import androidx.annotation.ColorInt
import bottomsheet.core.anatomy.button.Button
import bottomsheet.core.base.BaseMenu

class OverflowMenu(
    @ColorInt var overflowIconTintColor: Int? = null,
    override var buttons: MutableList<Button> = mutableListOf()
) : BaseMenu(buttons)