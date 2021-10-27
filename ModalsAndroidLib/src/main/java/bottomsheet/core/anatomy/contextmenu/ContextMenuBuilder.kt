package bottomsheet.core.anatomy.contextmenu

import bottomsheet.core.anatomy.button.Button

class ContextMenuBuilder internal constructor() {
    var model = ContextMenu()

    inline fun setAllButtons(modification: (oldButtons: MutableList<Button>) -> MutableList<Button>) = this.apply {
        model.buttons = modification(model.buttons)
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