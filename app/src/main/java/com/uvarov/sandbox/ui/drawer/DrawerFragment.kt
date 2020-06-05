package com.uvarov.sandbox.ui.drawer

import android.os.Bundle
import android.view.*
import androidx.customview.widget.Openable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide

import com.uvarov.sandbox.MainActivity
import com.uvarov.sandbox.R
import com.uvarov.sandbox.SandboxApplication
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.account.Account
import com.uvarov.sandbox.databinding.DrawerFragmentBinding
import com.uvarov.sandbox.di.drawer.DrawerModule
import com.uvarov.sandbox.utils.setupWithNavController
import javax.inject.Inject

class DrawerFragment : Fragment(), Openable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewBinding: DrawerFragmentBinding
    private lateinit var viewModel: DrawerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext!! as SandboxApplication).appComponent.createDrawerComponent(DrawerModule()).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = DrawerFragmentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DrawerViewModel::class.java)

        val navHostFragment: NavHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        viewBinding.navigation.setupWithNavController(navHostFragment.navController)

        viewModel.accountLD.observe(viewLifecycleOwner, Observer {
            showAccountInfo(it)
        })

        viewModel.requestAccount()
    }

    private fun showAccountInfo(account: Account?) {
        if (account != null) {
            Glide
                .with(requireContext())
                .load(account.avatar)
                .centerCrop()
                .into(viewBinding.avatarImg)

            viewBinding.nameTxt.text = account.name
            viewBinding.surnameTxt.text = account.surname
        }
    }

    override fun isOpen(): Boolean {
        return (activity as MainActivity).isDrawerOpen()
    }

    override fun open() {
        (activity as MainActivity).openDrawer()
    }

    override fun close() {
        (activity as MainActivity).closeDrawer()
    }
}