package bottomsheet.core.anatomy.button.configuration

import android.graphics.drawable.Drawable
import bottomsheet.core.anatomy.button.configuration.option.ConfigurationOption
import bottomsheet.core.anatomy.text.Text

class ConfigurationOptionsButtonBuilder internal constructor() {
    @PublishedApi
    internal var model = ConfigurationOptionsButton(
        text = "Button"
    ) { 0 }

    inline fun setText(modification: (oldText: CharSequence) -> CharSequence) = this.apply {
        model.text = modification(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.textColor = modification(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        model.icon = modification(model.icon)
    }

    fun getIcon() = model.icon

    inline fun setIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.iconTintColor = modification(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    inline fun setAllConfigurationOptions(modification: (oldConfigurationOptions: MutableList<ConfigurationOption>) -> MutableList<ConfigurationOption>) = this.apply {
        model.configurationOptions = modification(model.configurationOptions)
    }

    inline fun setStrokeColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.strokeColor = modification(model.strokeColor)
    }

    fun getStrokeColor() = model.strokeColor

    inline fun setStrokeWidth(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.strokeWidth = modification(model.strokeWidth)
    }

    fun getStrokeWidth() = model.strokeWidth

    fun getAllConfigurationOptions() = model.configurationOptions

    inline fun setNote(modification: (oldNote: Text?) -> Text?) = this.apply {
        model.note = modification(model.note)
    }

    fun getNote() = model.note

    fun setCurrentOptionIdProviderAction(currentOptionProviderAction: () -> Int) = this.apply {
        model.currentOptionIdProviderAction = currentOptionProviderAction
    }

    fun getCurrentOptionIdProviderAction() = model.currentOptionIdProviderAction

    inline fun setCurrentNonPersistentOptionId(modification: (oldId: Int?) -> Int?) = this.apply {
        model.currentNonPersistentOptionId = modification(model.currentNonPersistentOptionId)
    }

    fun getCurrentNonPersistentOptionId() = model.currentNonPersistentOptionId

    fun build() = model
}