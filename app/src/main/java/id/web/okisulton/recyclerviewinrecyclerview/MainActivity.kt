package id.web.okisulton.recyclerviewinrecyclerview

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog
import id.web.okisulton.recyclerviewinrecyclerview.`interface`.IFirebasLoadListener
import id.web.okisulton.recyclerviewinrecyclerview.adapter.MyGroupAdapter
import id.web.okisulton.recyclerviewinrecyclerview.model.ItemData
import id.web.okisulton.recyclerviewinrecyclerview.model.ItemGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IFirebasLoadListener {

    lateinit var dialog: AlertDialog
    lateinit var iFirebasLoadListener: IFirebasLoadListener
    lateinit var myData: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init
        dialog = SpotsDialog.Builder().setContext(this).build()
        myData =FirebaseDatabase.getInstance().getReference("MyData")
        iFirebasLoadListener = this

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getFirebaseData()
    }

    private fun getFirebaseData() {
        dialog.show()
        myData.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                iFirebasLoadListener.onFirebasLoadFailed(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val itemGroups = ArrayList<ItemGroup>()
                for (myDataSnapshot in snapshot.children){
                    val itemGroup = ItemGroup()
                    itemGroup.headerTitle = myDataSnapshot.child("headerTitle").getValue(true).toString()
                    val t = object:GenericTypeIndicator<ArrayList<ItemData>>(){}
                    itemGroup.listItem = myDataSnapshot.child("listItem").getValue(t)
                    itemGroups.add(itemGroup)
                }
                iFirebasLoadListener.onFirebaseLoadSuccess(itemGroups)
            }

        })
    }

    override fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>) {
        val adapter = MyGroupAdapter(this, itemGroupList)
        recyclerView.adapter = adapter
        dialog.dismiss()
    }

    override fun onFirebasLoadFailed(message: String) {
        dialog.dismiss()
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
    }
}