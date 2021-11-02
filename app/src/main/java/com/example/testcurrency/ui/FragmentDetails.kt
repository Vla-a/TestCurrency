package com.example.testcurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.testcurrency.R
import com.example.testcurrency.databinding.FragmentDetailsBinding
import com.example.testcurrency.viewModel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class FragmentDetails : Fragment() {
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(System.currentTimeMillis())

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        // observing the change in the message declared in SharedViewModel
        model.message.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            binding!!.tvNum.text = "${resources.getString(R.string.id)}: ${it.id}"
            binding!!.name.text = "${resources.getString(R.string.name)}: ${it.name}"
            binding!!.tvCharCod.text =
                "${resources.getString(R.string.Abbreviation)}: ${it.charCode}"
            binding!!.tvNumCod.text = "${resources.getString(R.string.Date)}: $date"
            binding!!.tvRate.text = "${resources.getString(R.string.Rate)}: ${it.rate}"
            binding!!.tvScale.text = "${resources.getString(R.string.Scale)}: ${it.scale}"
        })

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}