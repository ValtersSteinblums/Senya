package com.example.senya.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.senya.R
import com.example.senya.databinding.FragmentHomeBinding
import com.example.senya.ui.fragment.BaseFragment

class HomeFragment: BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = HomeFragmentController { attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }

        binding.epoxyRecyclerView.setController(epoxyController)
       // binding.recyclerView.addItemDecoration(DividerItemDecoration(requireActivity()), RecyclerView.VERTICAL)

        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner) { attractions ->
            epoxyController.attractions = attractions
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}