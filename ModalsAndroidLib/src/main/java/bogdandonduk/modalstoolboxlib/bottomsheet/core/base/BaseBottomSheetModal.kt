package bogdandonduk.modalstoolboxlib.bottomsheet.core.base

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import bogdandonduk.modalstoolboxlib.R
import bogdandonduk.modalstoolboxlib.bottomsheet.BottomSheetModalsConfig
import bogdandonduk.modalstoolboxlib.bottomsheet.BottomSheetModalsToolbox
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerBottomSheetDialogFragment
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInitializationViewModelHandler
import com.google.android.material.bottomsheet.BottomSheetBehavior

@PublishedApi
internal abstract class BaseBottomSheetModal<ViewBindingType : ViewBinding, ViewModelType : BaseBottomSheetModalViewModel>(
    override var viewBindingInflation: (layoutInflater: LayoutInflater, parent: ViewGroup?) -> ViewBindingType
) : BaseViewBindingHandlerBottomSheetDialogFragment<ViewBindingType>(viewBindingInflation),
        SingleAutomaticInitializationWithInitializationViewModelHandler<ViewModelType>
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInitializedViewModel(viewModelStore).run {
            model.modal = this@BaseBottomSheetModal

            saveInfo = requireArguments().getBoolean(BottomSheetModalsConfig.KEY_SAVE_INFO, true)
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        dialog.dismiss()

        getInitializedViewModel(viewModelStore).model.callbacks.onCancelAction?.invoke(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        BottomSheetModalsToolbox.getSavedModalModel(tag!!)?.modal = null

        getInitializedViewModel(viewModelStore).model.callbacks.onDismissAction?.invoke(this)
    }

    private fun expand() {
        BottomSheetBehavior.from(dialog!!.findViewById<FrameLayout>(R.id.design_bottom_sheet)).state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun transparifyBackground() {
        (view?.parent as View).run {
            setBackgroundColor(Color.TRANSPARENT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        transparifyBackground()

        dialog?.setOnShowListener {
            BottomSheetModalsToolbox.modalShowing = true

            expand()
        }

        draw(view, savedInstanceState)
    }

    abstract fun draw(view: View, savedInstanceState: Bundle?)
}