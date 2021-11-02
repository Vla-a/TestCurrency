package com.example.testcurrency.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.testcurrency.R
import com.example.testcurrency.databaseUser.UserEntity
import com.example.testcurrency.databinding.FragmentLoginBinding
import com.example.testcurrency.viewModel.UserViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentLogin : Fragment() {

    private val userViewModel: UserViewModel by viewModel()
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

        userViewModel.userLiveDataBd.observe(this.viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                binding!!.btnReset.visibility = View.VISIBLE
                binding!!.btnSubmit.visibility = View.VISIBLE
                binding!!.etUserName.visibility = View.VISIBLE
                binding!!.btnRegistration.visibility = View.INVISIBLE
                binding!!.tvUser.text = it[0].login
                binding!!.tvPassword.text = it[0].password
            } else {
                binding!!.btnRegistration.visibility = View.VISIBLE
            }
            Log.e("KEK", it.toString())
        })

        binding!!.btnRegistration.setOnClickListener {

            if (binding!!.etPassword.text.toString().trim()
                    .isNotBlank() && binding!!.etUserName.text.toString().trim().isNotBlank()
            ) {
                this.findNavController().navigate(FragmentLoginDirections.toMainFragment())
                userViewModel.addUserDb(
                    binding!!.etUserName.text.toString(),
                    binding!!.etPassword.text.toString()
                )
            } else {
                Toast.makeText(context,  R.string.No_name_or_password, Toast.LENGTH_SHORT).show()
            }
            binding!!.etUserName.setText("")
            binding!!.etPassword.setText("")
        }

        binding!!.btnSubmit.setOnClickListener {
            userViewModel.userLiveDataBd.observe(this.viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    if (binding!!.etUserName.text.toString() == binding!!.tvUser.text &&
                        binding!!.etPassword.text.toString() == binding!!.tvPassword.text
                    ) {
                        this.findNavController()
                            .navigate(FragmentLoginDirections.toMainFragment())
                    } else {
                        Toast.makeText(context,  R.string.Invalid_password_or_name, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding!!.etUserName.setText("")
            binding!!.etPassword.setText("")
        }

        binding!!.btnReset.setOnClickListener {
            val user =
                UserEntity(binding!!.tvUser.text.toString(), binding!!.tvPassword.text.toString())

            binding!!.etUserName.visibility = View.VISIBLE
            binding!!.btnRegistration.visibility = View.VISIBLE
            binding!!.btnReset.visibility = View.INVISIBLE
            binding!!.btnSubmit.visibility = View.INVISIBLE
            userViewModel.deleteUserBd(user)

            binding!!.etUserName.setText("")
            binding!!.etPassword.setText("")

            Toast.makeText(context, R.string.DELETE_USER, Toast.LENGTH_SHORT).show()
        }

        binding!!.btnReturn.setOnClickListener {
            activity?.finish()
        }
    }
}
