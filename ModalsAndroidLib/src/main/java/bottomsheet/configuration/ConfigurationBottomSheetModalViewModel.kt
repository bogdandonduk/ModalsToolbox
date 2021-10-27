package bottomsheet.configuration

import bogdandonduk.popupmenutoolboxlib.PopupMenuToolbox
import bottomsheet.BottomSheetModalsToolbox
import bottomsheet.core.base.BaseBottomSheetModalViewModel

internal class ConfigurationBottomSheetModalViewModel(
    key: String
) : BaseBottomSheetModalViewModel(key) {
    var currentPopupMenuBuilder = PopupMenuToolbox.buildPopupMenu()

    @Volatile
    var currentOpenPopupMenuAnchorViewListPosition: Int? = null

    override fun onCleared() {
        super.onCleared()

        PopupMenuToolbox.deletePopupMenuInfo(currentPopupMenuBuilder.getKey())
    }
}