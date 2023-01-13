package com.theviciousgames.dpimodifier.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.theviciousgames.dpimodifier.databinding.ItemPresetBinding
import com.theviciousgames.dpimodifier.model.Preset

class PresetAdapter(
    private val onClickListenerDelete: OnClickListenerDelete,
    private val onClickListenerSet: OnClickListenerSet
) :
    RecyclerView.Adapter<PresetAdapter.PresetViewHolder>() {

    class OnClickListenerDelete(val clickListener: (preset: Preset) -> Unit) {
        fun onClick(preset: Preset) = clickListener(preset)
    }

    class OnClickListenerSet(val clickListener: (preset: Preset) -> Unit) {
        fun onClick(preset: Preset) = clickListener(preset)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Preset>() {
        override fun areItemsTheSame(
            oldItem: Preset,
            newItem: Preset
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Preset,
            newItem: Preset
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PresetViewHolder, position: Int) {
        // val ride = differ.currentList[position]

        holder.bind(differ.currentList[position])
        // holder.itemView.setOnClickListener { onClickListener.onClick(ride) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rideBinding = ItemPresetBinding.inflate(inflater, parent, false)
        return PresetViewHolder(rideBinding)
    }

    inner class PresetViewHolder(val binding: ItemPresetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(preset: Preset) {
            with(binding)
            {
                button.text = "${preset.name}  ->  ${preset.value} dpi"
                buttonRemove.setOnClickListener {
                    onClickListenerDelete.clickListener(preset)
                }
                button.setOnClickListener {
                    onClickListenerSet.clickListener(preset)
                }
            }
        }
    }
}