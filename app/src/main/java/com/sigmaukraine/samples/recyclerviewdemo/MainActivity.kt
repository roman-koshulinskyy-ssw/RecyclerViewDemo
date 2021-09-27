package com.sigmaukraine.samples.recyclerviewdemo

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sigmaukraine.samples.recyclerviewdemo.databinding.ActivityMainBinding
import com.sigmaukraine.samples.recyclerviewdemo.model.Message
import com.sigmaukraine.samples.recyclerviewdemo.ui.MessageListAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val messageList: MutableList<Message> = mutableListOf()
    private lateinit var messageListAdapter: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageListAdapter = MessageListAdapter(messageList)
        binding.recyclerView.adapter = messageListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.fab.setOnClickListener { showInputDialog() }
    }

    private fun showInputDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.new_message)

        val input = EditText(this)
        input.hint = getString(R.string.new_message_hint)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton(getString(android.R.string.ok)) { _, _ ->
            messageList.add(
                Message(
                    text = input.text.toString(),
                    dateCreated = Date()
                )
            )
            messageListAdapter.notifyItemInserted(messageList.size - 1)
        }

        builder.setNegativeButton(
            getString(android.R.string.cancel)
        ) { dialog, _ -> dialog.cancel() }
        builder.show()
    }
}