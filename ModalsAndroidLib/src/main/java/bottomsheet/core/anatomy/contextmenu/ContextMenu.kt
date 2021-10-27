package bottomsheet.core.anatomy.contextmenu

import bottomsheet.core.anatomy.button.Button
import bottomsheet.core.base.BaseMenu

class ContextMenu(
    override var buttons: MutableList<Button> = mutableListOf()
) : BaseMenu(buttons)