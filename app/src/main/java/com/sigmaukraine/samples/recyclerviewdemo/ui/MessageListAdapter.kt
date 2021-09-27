package com.sigmaukraine.samples.recyclerviewdemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sigmaukraine.samples.recyclerviewdemo.databinding.ItemMessageBinding
import com.sigmaukraine.samples.recyclerviewdemo.model.Message

/**
 * Created by Roman Koshulinskyy on 9/27/21.
 *
 */
class MessageListAdapter(private val messageList: MutableList<Message>) :
    RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int = messageList.size

    class ViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) = with(binding) {
            messageText.text = item.text
            messageDate.text = item.dateCreated.toString()
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Click",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}