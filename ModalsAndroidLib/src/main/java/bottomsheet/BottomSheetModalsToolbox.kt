package bottomsheet

import bottomsheet.configuration.ConfigurationBottomSheetModalBuilder
import bottomsheet.core.anatomy.appearance.AppearanceBuilder
import bottomsheet.core.anatomy.button.ButtonBuilder
import bottomsheet.core.anatomy.callbacks.CallbacksBuilder
import bottomsheet.core.anatomy.contextmenu.ContextMenuBuilder
import bottomsheet.core.anatomy.overflowmenu.OverflowMenuBuilder
import bottomsheet.core.anatomy.text.TextBuilder
import bottomsheet.core.base.BaseBottomSheetModalModel
import bottomsheet.simple.SimpleBottomSheetModalBuilder

object BottomSheetModalsToolbox {
    internal val savedModalModels = mutableMapOf<String, BaseBottomSheetModalModel>()
    @Volatile internal var modalShowing = false

    @Synchronized
    internal fun saveModalModel(key: String, model: BaseBottomSheetModalModel) {
        if(savedModalModels.containsKey(key) && savedModalModels[key]!!::class.java != model::class.java)
            throw IllegalStateException("Model with this key but different type is already saved")
        else
            savedModalModels[key] = model
    }

    @PublishedApi
    @Suppress("UNCHECKED_CAST")
    internal fun <T : BaseBottomSheetModalModel> getSavedModalModelCast(key: String) = savedModalModels[key] as? T

    @PublishedApi
    internal fun getSavedModalModel(key: String) = savedModalModels[key]

    @Synchronized
    internal fun removeModalModel(key: String) {
        savedModalModels.remove(key)
    }

    @Synchronized
    internal fun dismissAllModals(removeModels: Boolean = true) {
        savedModalModels.run {
            forEach {
                it.value.modal?.dismiss()

                if(removeModels)
                    remove(it.key)
            }
        }
    }

    fun isModalShowing() = modalShowing

    fun isModalShowing(key: String) = savedModalModels[key] != null && savedModalModels[key]!!.modal != null && modalShowing

    fun buildSimpleModal(key: String = BottomSheetModalsConfig.RandomKeyGenerationUtils.generate()) = SimpleBottomSheetModalBuilder(key)

    fun buildConfigurationModal(key: String = BottomSheetModalsConfig.RandomKeyGenerationUtils.generate()) = ConfigurationBottomSheetModalBuilder(key)

    fun buildAppearance() = AppearanceBuilder()

    fun buildButton() = ButtonBuilder()

    fun buildCallbacks() = CallbacksBuilder()

    fun buildContextMenu() = ContextMenuBuilder()

    fun buildOverflowMenu() = OverflowMenuBuilder()

    fun buildText() = TextBuilder()
}