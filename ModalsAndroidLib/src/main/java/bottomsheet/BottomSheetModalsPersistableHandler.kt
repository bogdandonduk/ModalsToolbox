package bottomsheet

import bottomsheet.core.base.BaseBottomSheetModalBuilder

interface BottomSheetModalsPersistableHandler {
    var bottomSheetModalBuilders: MutableMap<String, BaseBottomSheetModalBuilder>
}