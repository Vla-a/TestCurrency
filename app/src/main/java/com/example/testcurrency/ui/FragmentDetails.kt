package com.example.testcurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.testcurrency.R
import com.example.testcurrency.databinding.FragmentDetailsBinding
import com.example.testcurrency.ui.MainFragment.Companion.KEY1
import com.example.testcurrency.ui.MainFragment.Companion.KEY2
import com.example.testcurrency.ui.MainFragment.Companion.KEY3
import com.example.testcurrency.ui.MainFragment.Companion.KEY4
import com.example.testcurrency.ui.MainFragment.Companion.KEY5
import com.example.testcurrency.ui.MainFragment.Companion.KEY6
import com.example.testcurrency.ui.MainFragment.Companion.KEY7
import com.example.testcurrency.ui.MainFragment.Companion.TEST

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

        setFragmentResultListener(TEST) { key, bundle ->

            binding!!.tvNum.text =
                "${resources.getString(R.string.Num)}: ${bundle.getString(KEY2)}"

            binding!!.name.text =
                "${resources.getString(R.string.name)}: ${bundle.getString(KEY3)}"


            binding!!.tvCharCod.text =
                "${resources.getString(R.string.CharCode)}: ${bundle.getString(KEY1)}"

            binding!!.tvNumCod.text =
                "${resources.getString(R.string.NumCod)}: ${bundle.getString(KEY4)}"


            binding!!.tvId.text =
                "${resources.getString(R.string.id)}: ${bundle.getString(KEY5)}"

            binding!!.tvRate.text =
                "${resources.getString(R.string.Rate)}: ${bundle.getString(KEY6)}"

            binding!!.tvScale.text =
                "${resources.getString(R.string.Scale)}: ${bundle.getString(KEY7)}"


            binding!!.btnReturn.setOnClickListener {
                it.findNavController().popBackStack()
            }
        }
}
}