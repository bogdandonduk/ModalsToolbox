package bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.overflowmenu

import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.button.Button

class OverflowMenuBuilder internal constructor() {
    @PublishedApi
    internal var model = OverflowMenu()

    inline fun setOverflowIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.overflowIconTintColor = modification.invoke(model.overflowIconTintColor)
    }

    fun getOverflowIconTintColor() = model.overflowIconTintColor

    inline fun setAllButtons(modification: (oldButtons: MutableList<Button>) -> MutableList<Button>) = this.apply {
        model.buttons = modification.invoke(model.buttons)
    }

    fun getAllButtons() = model.buttons

    fun addButton(button: Button, replace: Boolean = true) = this.apply {
        if(!model.buttons.contains(button))
            model.buttons.add(button)
        else if(replace) {
            val index = model.buttons.indexOf(button)

            model.buttons.remove(button)

            model.buttons.add(index, button)
        }
    }

    fun build() = model
}
