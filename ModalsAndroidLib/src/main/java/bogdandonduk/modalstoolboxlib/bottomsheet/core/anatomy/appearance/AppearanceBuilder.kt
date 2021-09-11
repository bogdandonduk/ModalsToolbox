package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.appearance

class AppearanceBuilder() {
    @PublishedApi
    internal var model = Appearance()

    inline fun setBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.backgroundColor = modification.invoke(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    inline fun setStrokeWidth(modification: (oldWidth: Int) -> Int) = this.apply {
        model.strokeWidth = modification.invoke(model.strokeWidth)
    }

    fun getStrokeWidth() = model.strokeWidth

    inline fun setStrokeColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.strokeColor = modification.invoke(model.strokeColor)
    }

    fun getStrokeColor() = model.strokeColor

    inline fun setDividerLinesColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.dividerLinesColor = modification.invoke(model.dividerLinesColor)
    }

    fun getDividerLinesColor() = model.dividerLinesColor

    inline fun setGenericTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.genericTextColor = modification.invoke(model.genericTextColor)
    }

    fun getGenericTextColor() = model.genericTextColor

    inline fun setGenericButtonTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.genericButtonTextColor = modification.invoke(model.genericButtonTextColor)
    }

    fun getGenericButtonTextColor() = model.genericTextColor

    inline fun setGenericIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.genericIconTintColor = modification.invoke(model.genericIconTintColor)
    }

    fun getGenericIconTintColor() = model.genericIconTintColor

    fun setCornerRadii(topLeftPx: Int, topRightPx: Int, bottomRightPx: Int, bottomLeftPx: Int) = this.apply {
        model.cornerRadiusTopLeftPx = topLeftPx
        model.cornerRadiusTopRightPx = topRightPx
        model.cornerRadiusBottomRightPx = bottomRightPx
        model.cornerRadiusBottomLeftPx = bottomLeftPx
    }

    inline fun setCornerRadiusTopLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomLeftPx = modification.invoke(model.cornerRadiusTopLeftPx)
    }

    fun getCornerRadiusTopLeftPx() = model.cornerRadiusTopLeftPx

    inline fun setCornerRadiusTopRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusTopRightPx = modification.invoke(model.cornerRadiusTopRightPx)
    }

    fun getCornerRadiusTopRightPx() = model.cornerRadiusTopRightPx

    inline fun setCornerRadiusBottomRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomRightPx = modification.invoke(model.cornerRadiusBottomRightPx)
    }

    fun getCornerRadiusBottomRightPx() = model.cornerRadiusBottomRightPx

    inline fun setCornerRadiusBottomLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomLeftPx = modification.invoke(model.cornerRadiusBottomLeftPx)
    }

    fun build() = model
}