package com.uvarov.sandbox.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.uvarov.sandbox.SandboxApplication
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.account.Account
import com.uvarov.sandbox.databinding.AccountFragmentBinding
import com.uvarov.sandbox.di.account.AccountModule
import javax.inject.Inject

class AccountFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewBinding: AccountFragmentBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext!! as SandboxApplication).appComponent.createAccountComponent(AccountModule()).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = AccountFragmentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AccountViewModel::class.java)

        viewBinding.logoutBtn.setOnClickListener {
            val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(requireActivity().applicationContext, gso);
            googleSignInClient.signOut()
                .addOnCompleteListener {
                    viewModel.logOut()
                    findNavController().navigate(AccountFragmentDirections.loginAction())
                }
        }

        viewModel.accountLD.observe(viewLifecycleOwner, Observer {
            showAccountInfo(it)
        })

        viewModel.requestAccount()
    }

    private fun showAccountInfo(account: Account?) {
        if (account != null) {
            viewBinding.idTxt.text = account.id
            viewBinding.nameTxt.text = account.name
            viewBinding.surnameTxt.text = account.surname

            Glide
                .with(requireContext())
                .load(account.avatar)
                .centerCrop()
                .into(viewBinding.avatarImg)
        }
    }
}