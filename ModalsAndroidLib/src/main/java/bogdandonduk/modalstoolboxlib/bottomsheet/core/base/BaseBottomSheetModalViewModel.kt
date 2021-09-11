package bogdandonduk.modalstoolboxlib.bottomsheet.core.base

import androidx.lifecycle.ViewModel
import bogdandonduk.modalstoolboxlib.bottomsheet.BottomSheetModalsToolbox

@PublishedApi
internal abstract class BaseBottomSheetModalViewModel(key: String) : ViewModel() {
    var model = BottomSheetModalsToolbox.getSavedModalModel(key)!!

    var saveInfo = true

    override fun onCleared() {
        if(!saveInfo)
            BottomSheetModalsToolbox.removeModalModel(model.key)

        BottomSheetModalsToolbox.modalShowing = false
    }
}