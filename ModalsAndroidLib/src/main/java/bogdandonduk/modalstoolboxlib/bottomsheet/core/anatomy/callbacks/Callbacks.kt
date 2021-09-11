package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.callbacks

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Callbacks(
    var onCancelAction: ((modal: BottomSheetDialogFragment) -> Unit)? = null,
    var onDismissAction: ((modal: BottomSheetDialogFragment) -> Unit)? = null
)