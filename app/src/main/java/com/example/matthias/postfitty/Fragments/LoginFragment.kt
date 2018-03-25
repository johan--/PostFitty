package com.example.matthias.postfitty.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthias.postfitty.Activity.LoginRegisterActivity
import com.example.matthias.postfitty.R
import com.example.matthias.postfitty.Utils.FragmentType
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    lateinit var loginRegisterActivity: LoginRegisterActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpTextView.setOnClickListener({
            loginRegisterActivity.handleFragment(FragmentType.REGISTER)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
