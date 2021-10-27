package bottomsheet.simple

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.modalstoolboxlib.databinding.LayoutSimpleBottomSheetModalTextItemBinding
import bogdandonduk.viewdatabindingwrapperslib.ViewBindingHandler
import bottomsheet.core.anatomy.text.Text

internal class SimpleBottomSheetModalAdapter(
    var title: Text,
    var textContent: MutableList<Text>,
    var hostActivity: FragmentActivity,
    var model: SimpleBottomSheetModalModel,
    var touchInterceptor: View
) : RecyclerView.Adapter<SimpleBottomSheetModalAdapter.ViewHolder>() {
    inner class ViewHolder(override var viewBinding: LayoutSimpleBottomSheetModalTextItemBinding) : RecyclerView.ViewHolder(viewBinding.root), ViewBindingHandler<LayoutSimpleBottomSheetModalTextItemBinding> {
        lateinit var item: Text

        init {
            viewBinding.root.run {
                setOnTouchListener { _, event ->
                    touchInterceptor.dispatchTouchEvent(event)

                    false
                }

                setOnLongClickListener {
                    if(textContent.isNotEmpty())
                        CommonToolbox.vibrateOneShot(hostActivity, 50)

                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutSimpleBottomSheetModalTextItemBinding.inflate(hostActivity.layoutInflater, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            item = if(position != 0) textContent[position - 1] else title

            viewBinding.run {
                if(item.icon != null) {
                    layoutSimpleBottomSheetModalItemIconContainerConstraintLayout.visibility = View.VISIBLE

                    layoutSimpleBottomSheetModalItemIconImageView.run {
                        layoutParams = if(position == 0)
                            ConstraintLayout.LayoutParams(80, 80)
                        else
                            ConstraintLayout.LayoutParams(60, 60)

                        setImageDrawable(item.icon)
                    }
                } else
                    layoutSimpleBottomSheetModalItemIconContainerConstraintLayout.visibility = View.GONE

                layoutSimpleBottomSheetModalItemTextTextView.run {
                    textSize = if(position == 0) 20f else 16f
                    setTextColor(item.textColor ?: model.appearance.genericTextColor)
                    setTypeface(null, if(position == 0) Typeface.BOLD else Typeface.NORMAL)

                    text = item.text
                }

                layoutSimpleBottomSheetModalItemUnderplaceholderLinearLayout.visibility = if(position == 0) View.VISIBLE else View.GONE
            }
        }
    }

    override fun getItemCount() = textContent.size + 1
}