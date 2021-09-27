package bogdandonduk.modalstoolboxlib.bottomsheet.simple

import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.appearance.Appearance
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.button.Button
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.callbacks.Callbacks
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.contextmenu.ContextMenu
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.overflowmenu.OverflowMenu
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.text.Text
import bogdandonduk.modalstoolboxlib.bottomsheet.core.base.BaseBottomSheetModalModel

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