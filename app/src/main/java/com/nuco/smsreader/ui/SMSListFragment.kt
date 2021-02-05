package com.nuco.smsreader.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nuco.smsreader.base.BaseFragment
import com.nuco.smsreader.databinding.FragmentSmsListBinding
import com.nuco.smsreader.ui.adapter.SMSListAdapter
import com.nuco.smsreader.viewmodel.SMSFirestoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * SMS listing fragment
 */
class SMSListFragment : BaseFragment() {
    // TODO: use either of Firestore or retrofit
    //    private val viewModel by viewModel<SMSViewModel>()
    private val viewModel by viewModel<SMSFirestoreViewModel>()

    private var _binding: FragmentSmsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSMSList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeSMSList() {
        viewModel.getSMSList().observe(viewLifecycleOwner, { result ->
            result.error?.let {
                if (it.msg != null) {
                    showToast(it.msg)
                } else {
                    showToast(getString(it.code!!))
                }
            }
            result.response?.let { response ->
                if (response.smsList.isNotEmpty()) {
                    binding.recyclerSms.adapter = SMSListAdapter(response.smsList.sortedByDescending { it.receivedTime })
                    binding.recyclerSms.visibility = View.VISIBLE
                    binding.tvNoSms.visibility = View.GONE
                } else {
                    binding.recyclerSms.visibility = View.GONE
                    binding.tvNoSms.visibility = View.VISIBLE
                }
            }
        })
    }
}