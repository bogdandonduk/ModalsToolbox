package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.text

import android.graphics.drawable.Drawable

class TextBuilder internal constructor() {
    @PublishedApi
    internal var model = Text(
        text = "Text"
    )

    inline fun setText(modification: (oldText: String) -> String) = this.apply {
        model.text = modification.invoke(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setIcon(modification: (oldIcon: Drawable?) -> Drawable) = this.apply {
        model.icon = modification.invoke(model.icon)
    }

    fun getIcon() = model.icon

    inline fun setIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.iconTintColor = modification.invoke(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    fun build() = model
}