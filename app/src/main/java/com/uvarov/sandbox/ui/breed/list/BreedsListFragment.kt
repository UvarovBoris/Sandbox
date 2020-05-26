package com.uvarov.sandbox.ui.breed.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.uvarov.sandbox.SandboxApplication
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.databinding.BreedsListFragmentBinding
import com.uvarov.sandbox.di.breed.list.BreedsListModule
import javax.inject.Inject


class BreedsListFragment : Fragment() {

    companion object {
        fun newInstance() = BreedsListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewBinding: BreedsListFragmentBinding

    private lateinit var viewModel: BreedsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext!! as SandboxApplication).appComponent.createBreedsListComponent(BreedsListModule()).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = BreedsListFragmentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.message.setOnClickListener {
            it.findNavController().navigate(BreedsListFragmentDirections.actionBreedsListFragmentToBreedDetailFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(BreedsListViewModel::class.java)

        viewModel.requestBreeds()
    }

}
