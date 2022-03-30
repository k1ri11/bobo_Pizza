package com.example.bobopizza.presentation.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bobopizza.R
import com.example.bobopizza.databinding.FragmentHomeBinding
import com.example.bobopizza.domain.adapters.CategoryAdapter
import com.example.bobopizza.domain.adapters.PizzaAdapter
import com.example.bobopizza.domain.adapters.SliderAdapter
import com.example.bobopizza.domain.viewmodels.HomeViewModel
import com.example.bobopizza.presentation.MainActivity


class HomeFragment : Fragment(), CategoryAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var pizzaAdapter: PizzaAdapter
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    var prevClick: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        viewModel.menuModel.observe(viewLifecycleOwner) {
            pizzaAdapter.pizzaList = it
        }
    }

    private fun setupRecycler() {
        pizzaAdapter = PizzaAdapter()
        sliderAdapter = SliderAdapter()
        categoryAdapter = CategoryAdapter(this)
        binding.apply {
            pizzaRv.adapter = pizzaAdapter
            pizzaRv.layoutManager = LinearLayoutManager(activity)
            pizzaRv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            slider.adapter = sliderAdapter
            slider.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            categoryRv.adapter = categoryAdapter
            categoryRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onItemClick(view: View) {
        val cardText = view.findViewById<TextView>(R.id.category_text)
        cardText.setTextColor((ContextCompat.getColor(requireContext(), R.color.red)))
        cardText.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red_bg))
        cardText.typeface = Typeface.DEFAULT_BOLD
        if (prevClick != null && prevClick != view){
            val prevCardText = prevClick!!.findViewById<TextView>(R.id.category_text)
            prevCardText.setTextColor((ContextCompat.getColor(requireContext(), R.color.black)))
            prevCardText.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_bg))
            prevCardText.typeface = Typeface.DEFAULT
        }
        prevClick = view
     }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}