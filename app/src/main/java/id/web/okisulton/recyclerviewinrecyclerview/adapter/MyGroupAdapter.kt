package id.web.okisulton.recyclerviewinrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.web.okisulton.recyclerviewinrecyclerview.R
import id.web.okisulton.recyclerviewinrecyclerview.model.ItemGroup

class MyGroupAdapter(private val context: Context,
                    private val dataList:List<ItemGroup>?): RecyclerView.Adapter<MyGroupAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTitle:TextView
        var recyclerViewList: RecyclerView
        var btnMore: Button

        init {
            itemTitle = view.findViewById(R.id.itemTitle) as TextView
            recyclerViewList = view.findViewById(R.id.recyclerViewList) as RecyclerView
            btnMore = view.findViewById(R.id.btnMore) as Button
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_group, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemTitle.text = dataList!![position].headerTitle

        var items = dataList[position].listItem

        val itemListAdapter = MyItemadapter(context, items)
        holder.recyclerViewList.setHasFixedSize(true)
        holder.recyclerViewList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerViewList.adapter = itemListAdapter
        holder.recyclerViewList.isNestedScrollingEnabled = false //Important
        holder.btnMore.setOnClickListener{
            Toast.makeText(context, "BTN More"+ dataList[position].headerTitle, Toast.LENGTH_SHORT).show()
        }
    }

}
