package bogdandonduk.modalstoolboxlib.bottomsheet.core.base

import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.appearance.Appearance
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.callbacks.Callbacks

internal abstract class BaseBottomSheetModalModel(
    open var key: String,
    open var appearance: Appearance,
    open var callbacks: Callbacks
) {
    var modal: BaseBottomSheetModal<*, *>? = null
}