package bottomsheet.core.anatomy.text

import android.graphics.drawable.Drawable

class TextBuilder internal constructor() {
    @PublishedApi
    internal var model = Text(
        text = "Text"
    )

    inline fun setText(modification: (oldText: CharSequence) -> CharSequence) = this.apply {
        model.text = modification(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.textColor = modification(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setIcon(modification: (oldIcon: Drawable?) -> Drawable) = this.apply {
        model.icon = modification(model.icon)
    }

    fun getIcon() = model.icon

    inline fun setIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.iconTintColor = modification(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    fun build() = model
}