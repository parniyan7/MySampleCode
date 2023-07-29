package ir.miare.challenge.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.miare.challenge.databinding.ItemPlayerBinding
import ir.miare.core.data.source.entity.fake.PlayerEntity
import ir.miare.core.util.DelegateAdapter
import ir.miare.core.util.DelegateAdapterItem


class PlayerAdapter(val onItemClick: (item: PlayerEntity) -> Unit) :
    DelegateAdapter<PlayerEntity, PlayerAdapter.ViewHolder>(PlayerEntity::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: PlayerEntity,
        viewHolder: ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
        position: Int
    ) {
        viewHolder.bind(model, position)
    }

    inner class ViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: PlayerEntity, position: Int) {

            binding.rank.text = item.team.rank.toString()
            binding.playerName.text = item.name
            binding.teamName.text = item.team.name

            binding.root.setOnClickListener{
                onItemClick.invoke(item)
            }

        }
    }
}
