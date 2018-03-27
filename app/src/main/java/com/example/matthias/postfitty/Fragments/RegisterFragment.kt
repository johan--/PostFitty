package com.example.matthias.postfitty.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.matthias.postfitty.Activity.LoginRegisterActivity

import com.example.matthias.postfitty.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    lateinit var loginRegisterActivity: LoginRegisterActivity
    var myAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun registerNewUser() {
        if (usernameEditText.text != null || emailEditText.text != null || passwordEditText.text != null) {
            myAuth.createUserWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString())
        } else {
            Toast.makeText(context, "ooops, invalid input?", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }
}
