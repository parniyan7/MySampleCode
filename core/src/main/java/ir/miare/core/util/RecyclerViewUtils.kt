package ir.miare.core.util

import android.annotation.SuppressLint
import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

interface DelegateAdapterItem {
    fun id(): Any
    fun content(): Any
    fun payload(other: Any): Payloadable = Payloadable.None

    interface Payloadable {
        object None : Payloadable
    }
}

abstract class DelegateAdapter<M, in VH : RecyclerView.ViewHolder>(val modelClass: Class<out M>) {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(
        model: M,
        viewHolder: VH,
        payloads: List<DelegateAdapterItem.Payloadable>,
        position: Int
    )

    open fun onViewRecycled(viewHolder: VH) = Unit
    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit
    open fun onViewAttachedToWindow(viewHolder: VH) = Unit
}

internal class DelegateAdapterItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem::class == newItem::class && oldItem.id() == newItem.id()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem.content() == newItem.content()

    override fun getChangePayload(oldItem: DelegateAdapterItem, newItem: DelegateAdapterItem): Any =
        oldItem.payload(newItem)

}

class MainCompositeAdapter(
    private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>,
    private val customViewType: ((position: Int) -> Int)? = null
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(DelegateAdapterItemDiffCallback()) {

    private val TAG = MainCompositeAdapter::class.java.simpleName

    override fun getItemViewType(position: Int): Int {
        Log.i(TAG, "getItemViewType: $position ")
        if (customViewType != null) {
            return customViewType.invoke(position)
        } else {
            return defaultItemViewType(position)
        }
    }

    private fun defaultItemViewType(position: Int): Int {
        Log.i(TAG, "defaultItemViewType: $position ")
        for (i in 0 until delegates.size()) {
            if (delegates[i].modelClass == getItem(position).javaClass) {
                return delegates.keyAt(i)
            }
        }
        throw NullPointerException("Can not get viewType for position $position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i(TAG, "onCreateViewHolder: view type : $viewType")
        Log.i(TAG, "onCreateViewHolder: view  : ${delegates[viewType]}")
        return delegates[viewType].createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: without payload")
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val delegateAdapter = delegates[getItemViewType(position)]

        if (delegateAdapter != null) {
            val delegatePayloads = payloads.map { it as DelegateAdapterItem.Payloadable }
            delegateAdapter.bindViewHolder(getItem(position), holder, delegatePayloads, position)
        } else {
            throw NullPointerException("can not find adapter for position $position")
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onViewAttachedToWindow(holder)
        super.onViewAttachedToWindow(holder)
    }

    class Builder {

        private var count: Int = 0
        private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>> =
            SparseArray()

        fun add(delegateAdapter: DelegateAdapter<out DelegateAdapterItem, *>): Builder {
            delegates.put(
                count++,
                delegateAdapter as DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>
            )
            return this
        }

        fun build(customViewType: ((position: Int) -> Int)? = null): MainCompositeAdapter {
            require(count != 0) { "Register at least one adapter" }
            return MainCompositeAdapter(delegates, customViewType)
        }
    }
}