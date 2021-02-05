package com.nuco.smsreader.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nuco.smsreader.R
import com.nuco.smsreader.app.Constants
import com.nuco.smsreader.app.Utils
import com.nuco.smsreader.databinding.ItemSmsBinding
import com.nuco.smsreader.model.SMSDetail
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Recycler view adapter to show SMS list
 */
class SMSListAdapter(private val smsList: List<SMSDetail>) : RecyclerView.Adapter<SMSListAdapter.ItemViewHolder>(),
        KoinComponent {
    private val utils: Utils by inject()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_sms, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SMSListAdapter.ItemViewHolder, position: Int) {
        holder.bindSMS(smsList[position])
    }

    override fun getItemCount(): Int {
        return smsList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSmsBinding.bind(itemView)
        private val tvAmount: TextView = binding.tvSmsAmount
        private val tvTime: TextView = binding.tvSmsTime

        fun bindSMS(sms: SMSDetail) {
            tvAmount.text = sms.amount
            tvTime.text = utils.formatDate(sms.receivedTime, Constants.SMS_DATE_TIME_FORMAT)
        }
    }
}