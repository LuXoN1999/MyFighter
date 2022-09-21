package com.example.myfighter.ui.fighter_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myfighter.model.Fighter
import com.example.myfighter.additional_functions.*
import com.example.myfighter.databinding.FragmentFighterDetailsBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class FighterDetailsFragment:Fragment() {
    private lateinit var binding: FragmentFighterDetailsBinding
    private val viewModel: FighterDetailsViewModel by viewModel()
    private val args: FighterDetailsFragmentArgs by navArgs()
    private var buttonStates = ConvertButtonStates()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFighterDetailsBinding.inflate(layoutInflater)
        binding.btnDetailsBack.setOnClickListener { showFighterListFragment() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fighter = viewModel.getFighterByName(args.fighterName)
        binding.btnWeightConvert.setOnClickListener { changeWeightUnit(buttonStates,fighter) }
        binding.btnHeightConvert.setOnClickListener { changeHeightUnit(buttonStates,fighter)}
        binding.btnDeleteFighter.setOnClickListener {
            fighter?.let { it ->
                viewModel.delete(it)
            }
            var toast = Toast.makeText(this.context,"FIGHTER SUCCESSFULLY DELETED!",Toast.LENGTH_LONG)
            toast.show()
            showFighterListFragment()
        }
        setupUI(fighter)
    }

    private fun setupUI(fighter: Fighter?) {
        if (fighter != null) {
            Picasso.with(this.context).load(fighter.imageURL).into(binding.ivDetailsFighterImage)
            binding.tvDetailsNameValue.text = fighter.name

            if(fighter.nickname.isNullOrEmpty()) binding.tvDetailsNicknameValue.text = "-"
            else binding.tvDetailsNicknameValue.text=fighter.nickname.toString()

            binding.tvDetailsNationalityValue.text = fighter.nationality
            binding.tvDetailsAgeValue.text = fighter.age.toString()
            binding.tvDetailsStatusValue.text = if (fighter.isRetired) "Retired" else "Active"
            binding.tvDetailsDivisionValue.text = fighter.division
            binding.tvDetailsWeightValue.text = fighter.weightKg.toString() + " kg"
            binding.tvDetailsHeightValue.text = fighter.heightCm.toString() + " cm"
            binding.tvDetailsStyleValue.text = fighter.fightingStyle
            binding.tvDetailsBmiValue.text = calculateBMI(fighter.weightKg,fighter.heightCm).toString()
            changeBMITextAndColorDependingOnValue(calculateBMI(fighter.weightKg,fighter.heightCm))
        }
    }

    private fun showFighterListFragment() {
        val action =
            FighterDetailsFragmentDirections.actionFighterDetailsFragmentToFighterListFragment()
        findNavController().navigate(action)
    }

    private fun changeWeightUnit(buttonStates: ConvertButtonStates, fighter: Fighter?) {
        if (fighter != null) {
            if (buttonStates.weightIsInKg) {
                buttonStates.switchWeightUnit()
                binding.tvDetailsWeightValue.text = convertKgToLbs(fighter.weightKg).toString() + " lbs"
            } else {
                buttonStates.switchWeightUnit()
                binding.tvDetailsWeightValue.text = convertLbsToKg(convertKgToLbs(fighter.weightKg)).toString() + " kg"
            }
        }
    }

    private fun changeHeightUnit(buttonStates: ConvertButtonStates,fighter: Fighter?){
        if(fighter != null){
            if(buttonStates.heightIsInCm){
                buttonStates.switchHeightUnit()
                binding.tvDetailsHeightValue.text = convertCmToFt(fighter.heightCm).toString() + " ft"
            }
            else {
                buttonStates.switchHeightUnit()
                binding.tvDetailsHeightValue.text = convertCmToFt(convertFtToCm(fighter.heightCm)).toString() + " cm"
            }
        }
    }

    private fun changeBMITextAndColorDependingOnValue(bmi: Double){
        val bmiTextView = binding.tvDetailsBmiMessageValue
        bmiTextView.text = returnBMIClassByBMI(bmi)
        bmiTextView.setTextColor(returnColorByBMI(bmi))
    }
}