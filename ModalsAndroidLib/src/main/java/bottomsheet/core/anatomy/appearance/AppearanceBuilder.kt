package bottomsheet.core.anatomy.appearance

import androidx.annotation.ColorInt

class AppearanceBuilder {
    @PublishedApi
    internal var model = Appearance()

    inline fun setBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.backgroundColor = modification(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    inline fun setStrokeWidth(modification: (oldWidth: Int) -> Int) = this.apply {
        model.strokeWidth = modification(model.strokeWidth)
    }

    fun getStrokeWidth() = model.strokeWidth

    inline fun setStrokeColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.strokeColor = modification(model.strokeColor)
    }

    fun getStrokeColor() = model.strokeColor

    inline fun setDividerLinesColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.dividerLinesColor = modification(model.dividerLinesColor)
    }

    fun getDividerLinesColor() = model.dividerLinesColor

    inline fun setGenericTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.genericTextColor = modification(model.genericTextColor)
    }

    fun getGenericTextColor() = model.genericTextColor

    inline fun setGenericButtonTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.genericButtonTextColor = modification(model.genericButtonTextColor)
    }

    fun getGenericButtonTextColor() = model.genericTextColor

    inline fun setGenericIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.genericIconTintColor = modification(model.genericIconTintColor)
    }

    fun getGenericIconTintColor() = model.genericIconTintColor

    inline fun setGenericContentStrokeWidth(modification: (oldWidth: Int) -> Int) = this.apply {
        model.genericContentStrokeWidth = modification(model.genericContentStrokeWidth)
    }

    fun getGenericContentStrokeWidth() = model.genericContentStrokeWidth

    inline fun setGenericContentStrokeColor(modification: (oldWidth: Int) -> Int) = this.apply {
        model.genericContentStrokeColor = modification(model.genericContentStrokeColor)
    }

    fun getGenericContentStrokeColor() = model.genericContentStrokeColor

    fun setCornerRadii(topLeftPx: Int, topRightPx: Int, bottomRightPx: Int, bottomLeftPx: Int) = this.apply {
        model.cornerRadiusTopLeftPx = topLeftPx
        model.cornerRadiusTopRightPx = topRightPx
        model.cornerRadiusBottomRightPx = bottomRightPx
        model.cornerRadiusBottomLeftPx = bottomLeftPx
    }

    inline fun setCornerRadiusTopLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomLeftPx = modification(model.cornerRadiusTopLeftPx)
    }

    fun getCornerRadiusTopLeftPx() = model.cornerRadiusTopLeftPx

    inline fun setCornerRadiusTopRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusTopRightPx = modification(model.cornerRadiusTopRightPx)
    }

    fun getCornerRadiusTopRightPx() = model.cornerRadiusTopRightPx

    inline fun setCornerRadiusBottomRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomRightPx = modification(model.cornerRadiusBottomRightPx)
    }

    fun getCornerRadiusBottomRightPx() = model.cornerRadiusBottomRightPx

    inline fun setCornerRadiusBottomLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomLeftPx = modification(model.cornerRadiusBottomLeftPx)
    }

    fun build() = model
}