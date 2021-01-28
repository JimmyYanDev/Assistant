package com.fadebad.assistant.ui.home

import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.fadebad.assistant.R

/**
 * Created by MichealYan on 2021/1/26.
 * Email: 956462326@qq.com
 * Describe:
 **/
class HomeAdapter(private val mainViewModel: HomeViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TITLE_VIEw = 0
        const val SWITCH_VIEw = 1
        const val INTENT_VIEw = 2
        const val DIVIDER_VIEw = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TITLE_VIEw -> {
                TitleViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.home_title_recycle_item, parent, false)
                )
            }
            SWITCH_VIEw -> {
                SwitchViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.home_switch_recycle_item, parent, false)
                )
            }
            INTENT_VIEw -> {
                IntentViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.home_intent_recycle_item, parent, false)
                )
            }
            else -> {
                DividerViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.home_divider_recycle_item, parent, false)
                )
            }
        }


    override fun getItemCount() = mainViewModel.shortCuts.value!!.size

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mainViewModel.shortCuts.value!![position]
        when (holder) {
            is TitleViewHolder -> {
                holder.titleText.text = data.label
            }
            is SwitchViewHolder -> {
                holder.shortCutLabel.text = data.label
                holder.shortCutIcon.setImageResource(data.icon)
                val lastValue = data.funcImpl?.get<Boolean>(holder.itemView.context)?.let { it } ?: false
                holder.shortCutIcon.imageTintList = ColorStateList.valueOf(
                        if (lastValue) 0xff05A7C4.toInt() else 0xff555555.toInt()
                )
                holder.itemView.setOnClickListener {
                    val lastValue = data.funcImpl?.get<Boolean>(holder.itemView.context)?.let { it } ?: false
                    val newValue = !lastValue
                    data.funcImpl?.set(holder.itemView.context, newValue)
                    holder.shortCutIcon.imageTintList = ColorStateList.valueOf(
                            if (newValue) 0xff05A7C4.toInt() else 0xff555555.toInt()
                    )
                }
            }
            is IntentViewHolder -> {
                holder.shortCutLabel.text = data.label
            }
            else -> {

            }
        }
    }

    override fun getItemViewType(position: Int) = mainViewModel.shortCuts.value!![position].type

    class SwitchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shortCutIcon = itemView.findViewById<ImageView>(R.id.shortCutIcon)
        val shortCutLabel = itemView.findViewById<TextView>(R.id.shortCutLabel)
        val shortCutSwitch = itemView.findViewById<Switch>(R.id.shortCutSwitch)
    }

    class IntentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shortCutIcon = itemView.findViewById<ImageView>(R.id.shortCutIcon)
        val shortCutLabel = itemView.findViewById<TextView>(R.id.shortCutLabel)

    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.titleText)

    }

    class DividerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}