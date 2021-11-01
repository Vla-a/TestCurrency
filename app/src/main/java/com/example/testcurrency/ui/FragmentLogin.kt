package com.example.testcurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myhomework.homework13.sharedprefs.SharedPrefsKeyss
import com.example.myhomework.homework13.sharedprefs.SharedPrefsUtilss
import com.example.testcurrency.R
import com.example.testcurrency.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {
    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.etLogin.text
        val s = SharedPrefsUtilss.putString(SharedPrefsKeyss.LOGIN, resources.getString(
            R.string.no_value))

        binding!!.confirm.setOnClickListener {

            val login = binding!!.etLogin.text.toString()
            val password = binding!!.etPassword.text

            SharedPrefsUtilss.putString(SharedPrefsKeyss.LOGIN, binding!!.etLogin.toString())
            SharedPrefsUtilss.putString(SharedPrefsKeyss.PASSWORD, binding!!.etPassword.toString())

              this.findNavController().navigate(FragmentLoginDirections.toMainFragment())


        }
    }

    override fun onPause() {
        super.onPause()

    }
}