package com.example.myfighter.ui.fighter_list

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myfighter.model.Fighter
import com.example.myfighter.additional_functions.calculateDivision
import com.example.myfighter.additional_functions.checkIfImageURLIsValid
import com.example.myfighter.additional_functions.shortenDivisionName
import com.example.myfighter.databinding.ItemFighterBinding
import com.squareup.picasso.Picasso

class FighterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(fighter: Fighter) {
        val binding = ItemFighterBinding.bind(itemView)
        binding.tvName.text = fighter.name
        binding.tvNickname.text = fighter.nickname
        binding.tvDivision.text = shortenDivisionName(calculateDivision(fighter.weightKg))
        if(checkIfImageURLIsValid(fighter.imageURL)){
            Picasso.with(itemView.context).load(fighter.imageURL).into(binding.ivFighterImage)
        }
        else {
            val imageUri = "https://cdn3.iconfinder.com/data/icons/mma-fighters/229/mma-fighter-007-512.png"
            Picasso.with(itemView.context).load(imageUri).into(binding.ivFighterImage)
        }

        if(fighter.isRetired){
            binding.tvItemStatus.text = "RETIRED"
            binding.tvItemStatus.setTextColor(Color.RED)
        }
        else{
            binding.tvItemStatus.text = "ACTIVE"
            binding.tvItemStatus.setTextColor(Color.GREEN)
        }
    }
}