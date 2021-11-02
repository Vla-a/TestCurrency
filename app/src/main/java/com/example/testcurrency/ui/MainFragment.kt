package com.example.testcurrency.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurrency.R
import com.example.testcurrency.data.CurrencyAdapter
import com.example.testcurrency.data.CurrencyResult
import com.example.testcurrency.databinding.FragmentMainBinding
import com.example.testcurrency.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val myViewModel: MainViewModel by viewModel()
    var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyAdapter = CurrencyAdapter { clickListener(it) }
        binding!!.rvCurrency.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.rvCurrency?.adapter = currencyAdapter

        if (context?.let { isOnline(it) } == false) {
            Toast.makeText(context, R.string.No_currency, Toast.LENGTH_SHORT).show()
        }

        myViewModel.currencyLiveDataBd.observe(this.viewLifecycleOwner, Observer {
            currencyAdapter.submitList(it)
        })

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun clickListener(currency: CurrencyResult) {
        this.findNavController().navigate(MainFragmentDirections.toFragmentDetails())

        setFragmentResult(TEST, Bundle().apply {
            putString(KEY1, currency.charCode)
            putString(KEY3, currency.name)
            putString(KEY4, currency.numCod)
            putString(KEY5, currency.id.toString())
            putString(KEY6, currency.rate.toString())
            putString(KEY7, currency.scale.toString())
        })
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    companion object {
        const val TEST = "TEST"
        const val KEY1 = "KEY1"
        const val KEY2 = "KEY2"
        const val KEY3 = "KEY3"
        const val KEY4 = "KEY4"
        const val KEY5 = "KEY5"
        const val KEY6 = "KEY6"
        const val KEY7 = "KEY7"

    }
}
