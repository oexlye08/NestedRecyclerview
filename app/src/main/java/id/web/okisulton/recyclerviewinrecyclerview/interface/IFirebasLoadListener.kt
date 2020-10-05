package id.web.okisulton.recyclerviewinrecyclerview.`interface`

import id.web.okisulton.recyclerviewinrecyclerview.model.ItemGroup

interface IFirebasLoadListener {
    fun onFirebaseLoadSuccess(itemGroupList:List<ItemGroup>)
    fun onFirebasLoadFailed(message:String)
}