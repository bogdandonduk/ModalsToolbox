package bottomsheet.core.anatomy.button.configuration.option

import android.graphics.drawable.Drawable

class ConfigurationOptionBuilder {
    @PublishedApi
    internal var model = ConfigurationOption(
        0, "Option"
    ) { true }

    inline fun setId(modification: (oldId: Int) -> Int) = this.apply {
        model.id = modification(model.id)
    }

    fun getId() = model.id

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

    inline fun setIconContentDescription(modification: (oldString: String?) -> String?) = this.apply {
        model.iconContentDescription = modification(model.iconContentDescription)
    }

    fun getIconContentDescription() = model.iconContentDescription

    inline fun setExecuteSelectedActionImmediately(modification: (oldValue: Boolean) -> Boolean) = this.apply {
        model.executeSelectedActionImmediately = modification(model.executeSelectedActionImmediately)
    }

    fun isExecutingSelectedActionImmediately() = model.executeSelectedActionImmediately

    inline fun setExecuteSelectedActionAgainWhenNotChanged(modification: (oldValue: Boolean) -> Boolean) = this.apply {
        model.executeSelectedActionAgainWhenNotChanged = modification(model.executeSelectedActionAgainWhenNotChanged)
    }

    fun isExecutingSelectedActionAgainWhenNotChanged() = model.executeSelectedActionAgainWhenNotChanged

    fun setSelectedAction(selectedAction: () -> Unit) = this.apply {
        model.selectedAction = selectedAction
    }

    fun getSelectedAction() = model.selectedAction

    fun build() = model
}