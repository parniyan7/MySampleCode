package ir.miare.challenge.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.miare.challenge.databinding.ItemLeagueBinding
import ir.miare.core.data.source.entity.fake.LeagueEntity
import ir.miare.core.util.DelegateAdapter
import ir.miare.core.util.DelegateAdapterItem


class LeagueAdapter :
    DelegateAdapter<LeagueEntity, LeagueAdapter.ViewHolder>(LeagueEntity::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun bindViewHolder(
        model: LeagueEntity,
        viewHolder: ViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
        position: Int
    ) {
        viewHolder.bind(model, position)
    }

    inner class ViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: LeagueEntity, position: Int) {

            binding.leagueTv.text = "${item.name} - ${item.country}"

        }
    }
}
