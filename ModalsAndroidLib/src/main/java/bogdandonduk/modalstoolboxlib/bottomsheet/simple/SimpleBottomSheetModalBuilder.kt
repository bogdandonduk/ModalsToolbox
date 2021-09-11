package bogdandonduk.modalstoolboxlib.bottomsheet.simple

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import bogdandonduk.modalstoolboxlib.bottomsheet.BottomSheetModalsConfig
import bogdandonduk.modalstoolboxlib.bottomsheet.BottomSheetModalsToolbox
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.appearance.Appearance
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.button.Button
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.callbacks.Callbacks
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.contextmenu.ContextMenu
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.overflowmenu.OverflowMenu
import bogdandonduk.modalstoolboxlib.bottomsheet.core.anatomy.text.Text
import bogdandonduk.modalstoolboxlib.bottomsheet.core.base.BaseBottomSheetModalBuilder

class SimpleBottomSheetModalBuilder internal constructor(key: String) : BaseBottomSheetModalBuilder(key) {
    @PublishedApi
    internal var model = BottomSheetModalsToolbox.getSavedModalModelCast<SimpleBottomSheetModalModel>(key).run {
        if(this != null)
            SimpleBottomSheetModalModel(
                key = key,
                appearance = appearance,
                title = title,
                textContent = textContent,
                positiveButton = positiveButton,
                negativeButton = negativeButton,
                contextMenu = contextMenu,
                overflowMenu = overflowMenu,
                callbacks = callbacks
            )
        else {
            BottomSheetModalsConfig.RandomKeyGenerationUtils.transientKeyRegistry.add(key)

            SimpleBottomSheetModalModel(
                key = key,
                appearance = Appearance(),
                title = Text(
                    "Title"
                ),
                textContent = mutableListOf(),

                positiveButton = Button("Confirm"),
                negativeButton = Button("Cancel"),

                contextMenu = ContextMenu(),
                overflowMenu = OverflowMenu(),

                callbacks = Callbacks()
            )
        }
    }

    inline fun setAppearance(modification: (oldAppearance: Appearance) -> Appearance) = this.apply {
        model.appearance = modification.invoke(model.appearance)
    }

    fun getAppearance() = model.appearance

    inline fun setTitle(modification: (oldTitle: Text) -> Text) = this.apply {
        model.title = modification.invoke(model.title)
    }

    fun getTitle() = model.title

    inline fun setAllTextContent(modification: (oldTextContent: MutableList<Text>) -> MutableList<Text>) = this.apply {
        model.textContent = modification.invoke(model.textContent)
    }

    fun getAllTextContent() = model.textContent

    fun addText(text: Text, replace: Boolean = true) = this.apply {
        if(!model.textContent.contains(text))
            model.textContent.add(text)
        else if(replace) {
            val index = model.textContent.indexOf(text)

            model.textContent.remove(text)

            model.textContent.add(index, text)
        }
    }

    inline fun setPositiveButton(modification: (oldButton: Button) -> Button) = this.apply {
        model.positiveButton = modification.invoke(model.positiveButton)
    }

    fun getPositiveButton() = model.positiveButton

    inline fun setNegativeButton(modification: (oldButton: Button) -> Button) = this.apply {
        model.negativeButton = modification.invoke(model.negativeButton)
    }

    fun getNegativeButton() = model.negativeButton

    inline fun setContextMenu(modification: (oldMenu: ContextMenu) -> ContextMenu) = this.apply {
        model.contextMenu = modification.invoke(model.contextMenu)
    }

    fun getContextMenu() = model.contextMenu

    inline fun setOverflowMenu(modification: (oldMenu: OverflowMenu) -> OverflowMenu) = this.apply {
        model.overflowMenu = modification.invoke(model.overflowMenu)
    }

    fun getOverflowMenu() = model.overflowMenu

    inline fun setCallbacks(modification: (oldCallbacks: Callbacks) -> Callbacks) = this.apply {
        model.callbacks = modification.invoke(model.callbacks)
    }

    fun getCallbacks() = model.callbacks

    fun save() = this.apply {
        BottomSheetModalsToolbox.saveModalModel(model.key, model)
    }

    fun show(fragmentManager: FragmentManager, addAsSecondOnTop: Boolean = false, saveInfo: Boolean = true) =
        if(addAsSecondOnTop || !BottomSheetModalsToolbox.modalShowing) {
            save()

            SimpleBottomSheetModal().apply {
                arguments = bundleOf(
                    BottomSheetModalsConfig.KEY_SAVE_INFO to saveInfo
                )
            }.show(fragmentManager, model.key)

            BottomSheetModalsConfig.RandomKeyGenerationUtils.transientKeyRegistry.remove(model.key)

            model.key
        } else null
}