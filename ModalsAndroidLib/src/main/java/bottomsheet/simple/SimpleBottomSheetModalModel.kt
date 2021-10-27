package bottomsheet.simple

import bottomsheet.core.anatomy.appearance.Appearance
import bottomsheet.core.anatomy.button.Button
import bottomsheet.core.anatomy.callbacks.Callbacks
import bottomsheet.core.anatomy.contextmenu.ContextMenu
import bottomsheet.core.anatomy.overflowmenu.OverflowMenu
import bottomsheet.core.anatomy.text.Text
import bottomsheet.core.base.BaseBottomSheetModalModel

@PublishedApi
internal class SimpleBottomSheetModalModel(
    override var key: String,

    override var appearance: Appearance,

    var title: Text,
    var textContent: MutableList<Text>,

    var positiveButton: Button,
    var negativeButton: Button?,

    var contextMenu: ContextMenu,
    var overflowMenu: OverflowMenu,

    override var callbacks: Callbacks
) : BaseBottomSheetModalModel(key, appearance, callbacks)