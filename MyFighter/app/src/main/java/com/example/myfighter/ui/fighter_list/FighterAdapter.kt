package com.example.myfighter.ui.fighter_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfighter.model.Fighter
import com.example.myfighter.R

class FighterAdapter: RecyclerView.Adapter<FighterViewHolder>() {
    private val fighters = mutableListOf<Fighter>()
    var onFighterEventListener: OnFighterEventListener? = null

    fun setFighters(fighters: List<Fighter>){
        this.fighters.clear()
        this.fighters.addAll(fighters)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FighterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fighter, parent, false)
        return FighterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FighterViewHolder, position: Int) {
        val fighter = fighters[position]
        holder.bind(fighter)
        onFighterEventListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onFighterSelected(fighter.name) }
        }
    }

    override fun getItemCount(): Int = fighters.count()


}