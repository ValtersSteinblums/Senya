package com.example.senya.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.databinding.FragmentAttractionDetailBinding
import com.squareup.picasso.Picasso

class AttractionDetailFragment: BaseFragment() {
    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView)
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.descriptionTextView.text = attraction.description

            binding.numbersOfFactsTextView.text = "${ attraction.facts.size } facts"
            binding.numbersOfFactsTextView.setOnClickListener {
                val stringBuilder = StringBuilder("")
                attraction.facts.forEach {
                    stringBuilder.append(it)
                    stringBuilder.append("\n\n")
                }
                val message = stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf("\n"))

                AlertDialog.Builder(requireContext())
                    .setTitle("${attraction.title} Facts")
                    .setMessage(message)
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemlocation -> {
                val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}