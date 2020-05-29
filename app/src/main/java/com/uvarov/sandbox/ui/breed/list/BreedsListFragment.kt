package com.uvarov.sandbox.ui.breed.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uvarov.sandbox.SandboxApplication
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.databinding.BreedsListFragmentBinding
import com.uvarov.sandbox.di.breed.list.BreedsListModule
import javax.inject.Inject


class BreedsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewBinding: BreedsListFragmentBinding
    private lateinit var viewModel: BreedsListViewModel
    private lateinit var breedsAdapter: BreedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext!! as SandboxApplication).appComponent.createBreedsListComponent(BreedsListModule()).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = BreedsListFragmentBinding.inflate(layoutInflater)

        breedsAdapter = BreedsAdapter() {
            findNavController().navigate(BreedsListFragmentDirections.breedDetailAction(it.name))
        }

        viewBinding.breedsRV.apply {
            setHasFixedSize(true)
            adapter = breedsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.getBreeds()
        }

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(BreedsListViewModel::class.java)

        viewModel.breedsLD.observe(viewLifecycleOwner, Observer {
            viewBinding.swipeRefresh.isRefreshing = false
            breedsAdapter.breeds = it
        })

        viewModel.getBreeds()
    }

}
