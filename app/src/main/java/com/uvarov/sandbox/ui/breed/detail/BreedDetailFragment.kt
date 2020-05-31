package com.uvarov.sandbox.ui.breed.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.uvarov.sandbox.SandboxApplication
import com.uvarov.sandbox.ViewModelFactory
import com.uvarov.sandbox.databinding.BreedDetailFragmentBinding
import com.uvarov.sandbox.di.breed.detail.BreedDetailModule
import javax.inject.Inject


class BreedDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val args: BreedDetailFragmentArgs by navArgs()
    private lateinit var viewBinding: BreedDetailFragmentBinding
    private lateinit var viewModel: BreedDetailViewModel
    private lateinit var breedImagesAdapter: BreedImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext!! as SandboxApplication).appComponent.createBreedDetailComponent(BreedDetailModule()).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = BreedDetailFragmentBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(BreedDetailViewModel::class.java)

        breedImagesAdapter = BreedImagesAdapter()

        viewBinding.breedImagesRV.apply {
            setHasFixedSize(true)
            adapter = breedImagesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.getBreedImages(args.breed)
        }

        viewModel.breedImagesLD.observe(viewLifecycleOwner, Observer {
            viewBinding.swipeRefresh.isRefreshing = false
            breedImagesAdapter.breedImages = it
        })

        if (savedInstanceState == null) {
            viewModel.getBreedImages(args.breed)
        }
    }

}
