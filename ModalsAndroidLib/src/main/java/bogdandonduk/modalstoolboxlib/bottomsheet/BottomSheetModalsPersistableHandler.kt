package bogdandonduk.modalstoolboxlib.bottomsheet

import bogdandonduk.modalstoolboxlib.bottomsheet.core.base.BaseBottomSheetModalBuilder

interface BottomSheetModalsPersistableHandler {
    var bottomSheetModalBuilders: MutableMap<String, BaseBottomSheetModalBuilder>
}