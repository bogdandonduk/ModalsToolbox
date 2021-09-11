package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.callbacks

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CallbacksBuilder internal constructor() {
    @PublishedApi
    internal var model = Callbacks()

    fun setOnCancelAction(onCancelAction: ((modal: BottomSheetDialogFragment) -> Unit)?) = this.apply {
        model.onCancelAction = onCancelAction
    }

    fun hasOnCancelAction() = model.onCancelAction != null

    fun removeOnCancelAction() = this.apply {
        model.onCancelAction = null
    }

    fun setOnDismissAction(onDismissAction: ((modal: BottomSheetDialogFragment) -> Unit)?) = this.apply {
        model.onDismissAction = onDismissAction
    }

    fun hasOnDismissAction() = model.onDismissAction

    fun removeOnDismissAction() = this.apply {
        model.onDismissAction = null
    }

    fun build() = model
}