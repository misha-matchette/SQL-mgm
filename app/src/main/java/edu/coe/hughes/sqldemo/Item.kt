package edu.coe.hughes.sqldemo

class Item {
    var name: String? = null
    var size:Int = 0

    constructor(){
        this.name = ""
        this.size = 0
    }

    constructor(name: String?, size: Int) {
        this.name = name
        this.size = size
    }
}
