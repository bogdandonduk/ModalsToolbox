package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.button

import android.graphics.drawable.Drawable
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ButtonBuilder internal constructor() {
    @PublishedApi
    internal var model = Button(
        text = "Button"
    )

    inline fun setText(modification: (oldText: String) -> String) = this.apply {
        model.text = modification.invoke(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        model.icon = modification.invoke(model.icon)
    }

    fun getIcon() = model.icon

    inline fun setIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.iconTintColor = modification.invoke(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    fun setOnClickAction(onClickAction: ((view: View, modal: BottomSheetDialogFragment) -> Unit)) = this.apply {
        model.onClickAction = onClickAction
    }

    fun hasOnClickAction() = model.onClickAction != null

    fun removeOnClickAction() = this.apply {
        model.onClickAction = null
    }

    fun build() = model
}