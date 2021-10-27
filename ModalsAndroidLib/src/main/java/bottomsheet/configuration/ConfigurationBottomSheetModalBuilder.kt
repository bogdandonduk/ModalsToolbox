package bottomsheet.configuration

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import bogdandonduk.popupmenutoolboxlib.PopupMenuToolbox
import bottomsheet.BottomSheetModalsConfig
import bottomsheet.BottomSheetModalsToolbox
import bottomsheet.core.anatomy.appearance.Appearance
import bottomsheet.core.anatomy.button.Button
import bottomsheet.core.anatomy.button.configuration.ConfigurationOptionsButton
import bottomsheet.core.anatomy.callbacks.Callbacks
import bottomsheet.core.anatomy.contextmenu.ContextMenu
import bottomsheet.core.anatomy.overflowmenu.OverflowMenu
import bottomsheet.core.anatomy.text.Text
import bottomsheet.core.base.BaseBottomSheetModalBuilder

class ConfigurationBottomSheetModalBuilder internal constructor(key: String) : BaseBottomSheetModalBuilder(key) {
    @PublishedApi
    internal var model = BottomSheetModalsToolbox.getSavedModalModelCast<ConfigurationBottomSheetModalModel>(key).run {
        if(this != null)
            ConfigurationBottomSheetModalModel(
                key = key,
                appearance = appearance,
                title = title,
                configurationOptionsButtons = configurationOptionsButtons,
                positiveButton = positiveButton,
                negativeButton = negativeButton,
                contextMenu = contextMenu,
                overflowMenu = overflowMenu,
                callbacks = callbacks
            )
        else {
            BottomSheetModalsConfig.RandomKeyGenerationUtils.transientKeyRegistry.add(key)

            ConfigurationBottomSheetModalModel(
                key = key,
                appearance = Appearance(),
                title = Text(
                    "Title"
                ),
                configurationOptionsButtons = mutableListOf(),

                positiveButton = Button("Confirm"),
                negativeButton = Button("Cancel"),

                contextMenu = ContextMenu(),
                overflowMenu = OverflowMenu(),

                callbacks = Callbacks()
            )
        }
    }

    inline fun setAppearance(modification: (oldAppearance: Appearance) -> Appearance) = this.apply {
        model.appearance = modification(model.appearance)
    }

    fun getAppearance() = model.appearance

    inline fun setTitle(modification: (oldTitle: Text) -> Text) = this.apply {
        model.title = modification(model.title)
    }

    fun getTitle() = model.title

    inline fun setAllConfigurationOptionsButton(modification: (oldTextContent: MutableList<ConfigurationOptionsButton>) -> MutableList<ConfigurationOptionsButton>) = this.apply {
        model.configurationOptionsButtons = modification(model.configurationOptionsButtons)
    }

    fun getAllConfigurationOptionsButton() = model.configurationOptionsButtons

    fun addConfigurationOptionsButton(button: ConfigurationOptionsButton, replace: Boolean = true) = this.apply {
        if(!model.configurationOptionsButtons.contains(button))
            model.configurationOptionsButtons.add(button)
        else if(replace) {
            val index = model.configurationOptionsButtons.indexOf(button)

            model.configurationOptionsButtons.remove(button)

            model.configurationOptionsButtons.add(index, button)
        }
    }

    inline fun setPositiveButton(modification: (oldButton: Button) -> Button) = this.apply {
        model.positiveButton = modification(model.positiveButton)
    }

    fun getPositiveButton() = model.positiveButton

    inline fun setNegativeButton(modification: (oldButton: Button?) -> Button?) = this.apply {
        model.negativeButton = modification(model.negativeButton)
    }

    fun getNegativeButton() = model.negativeButton

    inline fun setContextMenu(modification: (oldMenu: ContextMenu) -> ContextMenu) = this.apply {
        model.contextMenu = modification(model.contextMenu)
    }

    fun getContextMenu() = model.contextMenu

    inline fun setOverflowMenu(modification: (oldMenu: OverflowMenu) -> OverflowMenu) = this.apply {
        model.overflowMenu = modification(model.overflowMenu)
    }

    fun getOverflowMenu() = model.overflowMenu

    inline fun setCallbacks(modification: (oldCallbacks: Callbacks) -> Callbacks) = this.apply {
        model.callbacks = modification(model.callbacks)
    }

    fun getCallbacks() = model.callbacks

    fun save() = this.apply {
        BottomSheetModalsToolbox.saveModalModel(model.key, model)
    }

    fun show(fragmentManager: FragmentManager, addAsSecondOnTop: Boolean = false, saveInfo: Boolean = true) =
        if(addAsSecondOnTop || !BottomSheetModalsToolbox.modalShowing) {
            save()

            ConfigurationBottomSheetModal().apply {
                arguments = bundleOf(
                    BottomSheetModalsConfig.KEY_SAVE_INFO to saveInfo
                )
            }.show(fragmentManager, model.key)

            BottomSheetModalsConfig.RandomKeyGenerationUtils.transientKeyRegistry.remove(model.key)

            model.key
        } else null
}