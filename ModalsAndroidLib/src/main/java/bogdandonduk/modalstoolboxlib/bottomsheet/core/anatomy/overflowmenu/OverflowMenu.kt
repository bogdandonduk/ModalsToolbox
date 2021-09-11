package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.overflowmenu

import androidx.annotation.ColorInt
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.button.Button
import bogdandonduk.modalstoolboxlib.bottomsheet.core.base.BaseMenu

class OverflowMenu(
    @ColorInt var overflowIconTintColor: Int? = null,
    override var buttons: MutableList<Button> = mutableListOf()
) : BaseMenu(buttons)