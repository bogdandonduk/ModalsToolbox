package bottomsheet.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.listtoolboxlib.ListToolbox
import bogdandonduk.modalstoolboxlib.databinding.LayoutSimpleBottomSheetModalBinding
import bogdandonduk.popupmenutoolboxlib.PopupMenuItem
import bottomsheet.core.anatomy.button.configuration.ConfigurationOptionsButton
import bottomsheet.core.anatomy.button.configuration.option.ConfigurationOption
import bottomsheet.core.base.BaseBottomSheetModal
import top.defaults.drawabletoolbox.DrawableBuilder

internal class ConfigurationBottomSheetModal : BaseBottomSheetModal<LayoutSimpleBottomSheetModalBinding, ConfigurationBottomSheetModalViewModel>(
    { layoutInflater: LayoutInflater, viewGroup: ViewGroup? ->
        LayoutSimpleBottomSheetModalBinding.inflate(layoutInflater, viewGroup, false)
    }
) {
    override var viewModelInitialization = {
        ConfigurationBottomSheetModalViewModel(tag!!)
    }

    override fun draw(view: View, savedInstanceState: Bundle?) {
        (getInitializedViewModel(viewModelStore).model as ConfigurationBottomSheetModalModel).run {
            val rippleColor = CommonToolbox.getRippleColorByLuminance(requireActivity(), appearance.backgroundColor)

            /** APPEARANCE */
            viewBinding.layoutSimpleBottomSheetModalContentContainerConstraintLayout.background = DrawableBuilder()
                .solidColor(appearance.backgroundColor)
                .cornerRadii(appearance.cornerRadiusTopLeftPx, appearance.cornerRadiusTopRightPx, appearance.cornerRadiusBottomRightPx, appearance.cornerRadiusBottomLeftPx)
                .strokeWidth(appearance.strokeWidth)
                .strokeColor(appearance.strokeColor)
                .ripple()
                .rippleColor(rippleColor)
                .build()

            if(contextMenu.buttons.isNotEmpty())
                viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout.let {
                    it.background = DrawableBuilder()
                        .cornerRadii(appearance.cornerRadiusTopLeftPx, appearance.cornerRadiusTopRightPx, appearance.cornerRadiusBottomRightPx, appearance.cornerRadiusBottomLeftPx)
                        .ripple()
                        .rippleColor(rippleColor)
                        .build()

                    it.setOnClickListener {

                    }
                }

            /** CONFIGURATION OPTIONS */
            ListToolbox.initializeList(
                requireActivity(),
                viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView,
                ConfigurationBottomSheetModalAdapter(
                    title = title,
                    configurationOptionsButtons = configurationOptionsButtons,
                    hostActivity = requireActivity(),
                    model = this,
                    touchInterceptor = viewBinding.layoutSimpleBottomSheetModalTouchConstraintLayout
                )
            )

            /** POSITIVE BUTTON */
            viewBinding.layoutSimpleBottomSheetModalPositiveButtonTextView.let {
                it.background = DrawableBuilder()
                    .cornerRadius(1000000)
                    .ripple()
                    .rippleColor(rippleColor)
                    .build()

                it.setTextColor(positiveButton.textColor ?: appearance.genericButtonTextColor ?: appearance.genericTextColor)

                it.text = positiveButton.text

                it.setOnClickListener { view ->
                    dismiss()

                    configurationOptionsButtons.forEach {
                        val currentPersistentOptionId = it.currentOptionIdProviderAction()
                        
                        it.configurationOptions[it.currentNonPersistentOptionId ?: currentPersistentOptionId].run {
                            if(executeSelectedActionAgainWhenNotChanged || id != currentPersistentOptionId)
                                selectedAction()
                        }
                    }
                    
                    positiveButton.onClickAction?.invoke(view, this@ConfigurationBottomSheetModal)
                }

                it.post {
                    if(it.layout.getEllipsisCount(1) > 0)
                        it.setOnLongClickListener {

                            true
                        }
                }
            }

            if(negativeButton != null) {
                /** NEGATIVE BUTTON */
                viewBinding.layoutSimpleBottomSheetModalNegativeButtonTextView.let {
                    it.background = DrawableBuilder()
                        .cornerRadius(1000000)
                        .ripple()
                        .rippleColor(rippleColor)
                        .build()

                    it.setTextColor(positiveButton.textColor ?: appearance.genericButtonTextColor ?: appearance.genericTextColor)

                    it.text = negativeButton?.text

                    it.setOnClickListener { view ->
                        dismiss()

                        negativeButton?.onClickAction?.invoke(view, this@ConfigurationBottomSheetModal)
                    }

                    it.post {
                        if(it.layout.getEllipsisCount(1) > 0)
                            it.setOnLongClickListener {

                                true
                            }
                    }
                }

                viewBinding.layoutSimpleBottomSheetModalButtonDividerLinearLayout.setBackgroundColor(appearance.dividerLinesColor)
            } else {
                viewBinding.layoutSimpleBottomSheetModalNegativeButtonTextView.visibility = View.GONE
                viewBinding.layoutSimpleBottomSheetModalButtonDividerLinearLayout.visibility = View.GONE
            }

            /** OVERFLOW MENU BUTTON */
            if(overflowMenu.buttons.isNotEmpty()) {
                viewBinding.layoutSimpleBottomSheetModalButtonDivider2ContainerConstraintLayout.visibility = View.VISIBLE
                viewBinding.layoutSimpleBottomSheetModalButtonDivider2LinearLayout.setBackgroundColor(appearance.dividerLinesColor)

                if(overflowMenu.overflowIconTintColor != null)
                    CommonToolbox.applyColorFilter(viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonIconImageView.drawable, overflowMenu.overflowIconTintColor!!)
                else if(appearance.genericIconTintColor != null)
                    CommonToolbox.applyColorFilter(viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonIconImageView.drawable, appearance.genericIconTintColor!!)

                viewBinding.layoutSimpleBottomSheetModalMoreOptionsButtonContainerConstraintLayout.let {
                    it.visibility = View.VISIBLE

                    it.background = DrawableBuilder()
                        .cornerRadius(1000000)
                        .ripple()
                        .rippleColor(rippleColor)
                        .build()

                    it.setOnClickListener {

                    }

                    it.setOnLongClickListener {
                        true
                    }
                }
            }
        }
    }

    fun buildConfigurationPopupMenu(options: ConfigurationOptionsButton, buttonView: View, adapterPosition: Int, model: ConfigurationBottomSheetModalModel) = getInitializedViewModel(viewModelStore).currentPopupMenuBuilder
        .setAllMenuItems {
            mutableListOf<PopupMenuItem>().apply {
                options.configurationOptions.forEach {
                    add(
                        PopupMenuItem(
                            text = it.text,
                            textColor = it.textColor,
                            icon = it.icon,
                            iconTintColor = it.iconTintColor,
                            iconContentDescription = it.iconContentDescription,
                            isChecked = it.id == options.currentOptionIdProviderAction(),
                        ) { _: String?, _: View, _: PopupWindow ->
                            options.currentNonPersistentOptionId = it.id

                            viewBinding.layoutSimpleBottomSheetModalTextContainerRecyclerView.adapter?.notifyItemChanged(adapterPosition)

                            if(it.executeSelectedActionImmediately)
                                it.selectedAction()
                        }
                    )
                }
            }
        }
        .setOnShowAction { _, _ ->
            val rippleColor = CommonToolbox.getRippleColorByLuminance(requireContext(), model.appearance.backgroundColor)

            buttonView.background =
                DrawableBuilder()
                    .solidColor(rippleColor)
                    .ripple()
                    .rippleColor(CommonToolbox.getRippleColorByLuminance(requireContext(), rippleColor))
                    .strokeColor(options.strokeColor ?: model.appearance.genericContentStrokeColor)
                    .strokeWidth(options.strokeWidth ?: model.appearance.genericContentStrokeWidth)
                    .cornerRadii(options.cornerRadiusTopLeftPx, options.cornerRadiusTopRightPx, options.cornerRadiusBottomRightPx, options.cornerRadiusBottomLeftPx)
                    .build()

        }
        .setOnDismissAction {
            buttonView.background =
                DrawableBuilder()
                    .ripple()
                    .rippleColor(CommonToolbox.getRippleColorByLuminance(requireContext(), model.appearance.backgroundColor))
                    .strokeColor(options.strokeColor ?: model.appearance.genericContentStrokeColor)
                    .strokeWidth(options.strokeWidth ?: model.appearance.genericContentStrokeWidth)
                    .cornerRadii(options.cornerRadiusTopLeftPx, options.cornerRadiusTopRightPx, options.cornerRadiusBottomRightPx, options.cornerRadiusBottomLeftPx)
                    .build()
        }
}