package com.uvarov.sandbox.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.uvarov.sandbox.MainActivity
import com.uvarov.sandbox.R
import com.uvarov.sandbox.SandboxApplication
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.account.GoogleAccountFactory
import com.uvarov.sandbox.databinding.LoginFragmentBinding
import com.uvarov.sandbox.di.login.LoginModule
import com.uvarov.sandbox.utils.SingleObserver
import com.uvarov.sandbox.utils.toastShort
import timber.log.Timber
import javax.inject.Inject


class LoginFragment : Fragment() {

    companion object {
        private const val GOOGLE_LOGIN_REQUEST_CODE: Int = 1
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewBinding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext!! as SandboxApplication).appComponent.createLoginComponent(LoginModule()).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = LoginFragmentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        (activity as MainActivity).disableDrawer()

        viewBinding.googleLoginBtn.setOnClickListener {
            val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(requireActivity().applicationContext, gso);
            startActivityForResult(googleSignInClient.signInIntent, GOOGLE_LOGIN_REQUEST_CODE)
        }

        viewModel.moveBreedsLD.observe(viewLifecycleOwner, SingleObserver {
            (activity as MainActivity).enableDrawer()
            findNavController().navigate(LoginFragmentDirections.breedsListAction())
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_LOGIN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val googleAccount: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                viewModel.handleLogIn(GoogleAccountFactory(googleAccount).createAccount())
            } catch (e: ApiException) {
                requireContext().toastShort(R.string.requestError)
                Timber.e(e)
            }
        }
    }
}