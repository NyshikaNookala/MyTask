package com.example.mytask


object DataObject {
    var listdata = mutableListOf<CardInfo>()


    fun setData(title: String, description: String,duedate:String,priority: String,category:String,taskStatus:String) {
        listdata.add(CardInfo(title,description,duedate, priority,category,taskStatus))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title:String,description: String,duedate:String,priority: String,category:String,taskStatus:String)
    {
        listdata[pos].title=title
        listdata[pos].description=description
        listdata[pos].dueDate=duedate
        listdata[pos].priority=priority
        listdata[pos].category=category
        listdata[pos].taskStatus=taskStatus
    }

}