package com.example.userapp

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userapp.Fragment2


class UserAdapter(val context: Fragment2, val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_view, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.cardName.text= users[position].firstName
        holder.cardPhone.text= users[position].phone.toString()
        holder.cardEmail.text= users[position].email
        Glide.with(context).load(users[position].image).into(holder.cardImage);

        var color = "#A0A0A0"

        if(position%2==0) {
            color = "#F0F0F0"
        }

        var number: String? = ""
        holder.cardBody.setBackgroundColor(Color.parseColor(color))

        holder.cardBtn.setOnClickListener{
            number = holder.cardPhone.text.toString().trim()

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ Uri.encode(number)) )
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardName = itemView.findViewById<TextView>(R.id.card_name)
        val cardPhone = itemView.findViewById<TextView>(R.id.card_phone)
        val cardEmail = itemView.findViewById<TextView>(R.id.card_email)
        val cardBody = itemView.findViewById<LinearLayout>(R.id.card_body)
        val cardImage = itemView.findViewById<ImageView>(R.id.card_image)
        val cardBtn = itemView.findViewById<Button>(R.id.card_button)
    }
}

