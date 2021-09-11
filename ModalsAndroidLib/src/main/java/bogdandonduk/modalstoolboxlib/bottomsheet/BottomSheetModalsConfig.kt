package bogdandonduk.modalstoolboxlib.bottomsheet

import bogdandonduk.randomkeygenerator.RandomKeyGenerator

object BottomSheetModalsConfig {
    internal const val KEY_SAVE_INFO = "key_save_info"

    object RandomKeyGenerationUtils {
        internal val transientKeyRegistry = mutableListOf<String>()

        fun generate() = RandomKeyGenerator.generateWithPrefix("popup_menu") {
            BottomSheetModalsToolbox.getSavedModalModel(it) != null || transientKeyRegistry.contains(it)
        }

        fun getRandomKeyWithClassSuffix(host: Any) = "${generate()}_${host::class.java}"
    }

    object KeysExtensionVocabulary
}