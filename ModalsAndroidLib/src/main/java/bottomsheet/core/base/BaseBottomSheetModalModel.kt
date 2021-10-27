package bottomsheet.core.base

import bottomsheet.core.anatomy.appearance.Appearance
import bottomsheet.core.anatomy.callbacks.Callbacks

internal abstract class BaseBottomSheetModalModel(
    open var key: String,
    open var appearance: Appearance,
    open var callbacks: Callbacks
) {
    var modal: BaseBottomSheetModal<*, *>? = null
}