package com.example.myfighter.ui.new_fighter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myfighter.additional_functions.*
import com.example.myfighter.databinding.FragmentNewFighterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewFighterFragment: Fragment() {
    private lateinit var binding: FragmentNewFighterBinding
    private val viewModel: NewFighterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewFighterBinding.inflate(layoutInflater)
        binding.btnSaveFighter.setOnClickListener{saveFighter()}
        return binding.root
    }

    private fun saveFighter() {
        var message: Toast
        val name= binding.etName.text.toString()
        val nickname = binding.etNickname.text.toString()
        val nationality = binding.etNationality.text.toString()
        var imageURL = binding.etImageUrl.text.toString()
        val age = binding.etAge.text.toString()
        val weight = binding.etWeight.text.toString()
        val height = binding.etHeight.text.toString()
        val isRetired = returnRetirementRadioGroupValue()
        val style = returnFightingStyleRadioGroupValue()
        if(!inputsAreEmpty(name,nationality,age,weight,height)
            && atLeastOneButtonIsSelectedInRadiogroup(binding.rgStatus)
            && atLeastOneButtonIsSelectedInRadiogroup(binding.rgStyle))
        {
            if(imageURL.isNullOrEmpty()){ imageURL = "/" }
            val division = calculateDivision(weight.toDouble())
            viewModel.save(name,nickname,nationality,age.toInt(),isRetired,weight.toDouble(),division,height.toDouble(),style,imageURL)
            message = Toast.makeText(this.context,"FIGHTER CREATION SUCCESSFUL!",Toast.LENGTH_LONG)
            message.show()
            showFighterListFragment()
        }
        else{
            message = Toast.makeText(this.context,"INVALID INPUT!",Toast.LENGTH_LONG)
            message.show()
        }
    }

    private fun returnRetirementRadioGroupValue(): Boolean {
        val statusRadiogroup = binding.rgStatus
        var selectedId = statusRadiogroup.checkedRadioButtonId
        if(selectedId == binding.rbStatusActive.id){
            return false
        }
        return true
    }

    private fun returnFightingStyleRadioGroupValue(): String{
        val styleRadiogroup = binding.rgStyle
        var selectedId = styleRadiogroup.checkedRadioButtonId
        if(selectedId == binding.rbStyleMma.id){ return "MMA" }
        else if(selectedId == binding.rbStyleWrestler.id){ return "Wrestler" }
        else if(selectedId == binding.rbStyleKickboxing.id){ return "Kickboxer" }
        else if(selectedId == binding.rbStyleGrappler.id){ return "Grappler" }
        else return "None"
    }

    private fun showFighterListFragment(){
        val action = NewFighterFragmentDirections.actionNewFighterFragmentToFighterListFragment()
        findNavController().navigate(action)
    }

}