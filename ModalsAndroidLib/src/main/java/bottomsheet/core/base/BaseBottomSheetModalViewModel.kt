package bottomsheet.core.base

import androidx.lifecycle.ViewModel
import bottomsheet.BottomSheetModalsToolbox

@PublishedApi
internal abstract class BaseBottomSheetModalViewModel(key: String) : ViewModel() {
    var model = BottomSheetModalsToolbox.getSavedModalModel(key)!!

    var saveInfo = true

    override fun onCleared() {
        if(!saveInfo)
            BottomSheetModalsToolbox.deleteSavedModalInfo(model.key)

        BottomSheetModalsToolbox.modalShowing = false
    }
}