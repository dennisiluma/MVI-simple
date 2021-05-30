package com.dennis.mymvitask.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dennis.mymvitask.R
import com.dennis.mymvitask.data.model.Post
import com.dennis.mymvitask.ui.main.view.PostDetailActivity

class PostAdapter:RecyclerView.Adapter<PostAdapter.MyViewHolder>(){
    private var mylist = emptyList<Post>()


    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.tvPostTitle)

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PostDetailActivity::class.java).apply {
                    putExtra("USER_id", mylist[super.getLayoutPosition()].userId.toString())
                    putExtra("POST_ID", mylist[super.getLayoutPosition()].id.toString())
                    putExtra("TITLE", mylist[super.getLayoutPosition()].title)
                    putExtra("BODY", mylist[layoutPosition].body)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_post_item, parent, false))

    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = mylist[position].title
    }
    //this function colects api response data when it is called in the mainActivity.
    // it then stores the data to the myList decleared above for use by the recycler adapter
    fun setData(newList:List<Post>){
        mylist = newList
        notifyDataSetChanged()
    }
}