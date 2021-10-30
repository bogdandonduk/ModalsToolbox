package bottomsheet.configuration.popupmenu

class ConfigurationPopupMenuAppearanceBuilder {
    @PublishedApi
    internal var model = ConfigurationPopupMenuAppearance()

    inline fun setBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.backgroundColor = modification(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    inline fun setTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.textColor = modification(model.textColor)
    }

    fun getTextColor() = model.textColor

    fun build() = model
}