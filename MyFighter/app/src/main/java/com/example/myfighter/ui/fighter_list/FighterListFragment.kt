package com.example.myfighter.ui.fighter_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfighter.R
import com.example.myfighter.databinding.FragmentFighterListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FighterListFragment: Fragment(), OnFighterEventListener{
    private lateinit var binding: FragmentFighterListBinding
    private lateinit var adapter: FighterAdapter
    private val viewModel: FighterListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFighterListBinding.inflate(layoutInflater)
        binding.btnAddFighter.setOnClickListener{ showNewFighterFragment() }
        val divisionSpinner = binding.spDivisionFilter
        val retirementStatusSpinner = binding.spRetirementStatusFilter
        val fightingStyleSpinner = binding.spFightingStyleFilter
        setupSpinners(divisionSpinner,retirementStatusSpinner,fightingStyleSpinner)
        setSpinnerOnClickListeners(divisionSpinner,retirementStatusSpinner,fightingStyleSpinner)
        setupRecyclerView()
        observeFighters()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvFighters.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = FighterAdapter()
        adapter.onFighterEventListener= this
        binding.rvFighters.adapter = adapter
    }

    private fun setupSpinners(divisionSpinner: Spinner, statusSpinner: Spinner, fightingStyleSpinner: Spinner){
        ArrayAdapter.createFromResource(this.requireContext(), R.array.division_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            divisionSpinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(this.requireContext(), R.array.retirement_status_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            statusSpinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(this.requireContext(), R.array.fighting_style_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fightingStyleSpinner.adapter = adapter
        }
    }

    private fun setSpinnerOnClickListeners(divisionSpinner: Spinner, statusSpinner: Spinner, fightingStyleSpinner: Spinner){
        divisionSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.filterFightersByDivision(parent?.selectedItem.toString())
                observeFighters()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.filterFightersByDivision("All")
                observeFighters()
            }
        }

        statusSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.filterFightersByStatus(parent?.selectedItem.toString())
                observeFighters()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.filterFightersByStatus("All")
                observeFighters()
            }
        }

        fightingStyleSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.filterFightersByFightingStyle(parent?.selectedItem.toString())
                observeFighters()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.filterFightersByFightingStyle("All")
                observeFighters()
            }
        }
    }

    override fun onFighterSelected(name: String) {
        val action = FighterListFragmentDirections.actionFighterListFragmentToFighterDetailsFragment(name)
        findNavController().navigate(action)
    }

    private fun showNewFighterFragment() {
        val action = FighterListFragmentDirections.actionFighterListFragmentToNewFighterFragment()
        findNavController().navigate(action)
    }

    private fun observeFighters(){
        viewModel.fighters.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setFighters(it)
            }
        }
    }
}
