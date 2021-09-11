package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.contextmenu

import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.button.Button
import bogdandonduk.modalstoolboxlib.bottomsheet.core.base.BaseMenu

class ContextMenu(
    override var buttons: MutableList<Button> = mutableListOf()
) : BaseMenu(buttons)