package jp.zhengze.todoappsample.data
data class TodoWithGategory(val date:String? ="",val  todo:String=" ", val gategory: Gategory?)

abstract class Item

data class HeadItem(val gategory: Gategory):Item()

data class TodoItem(val date:String, val todo: String):Item()

enum class Gategory(value:String){
    PERSONAL("個人"),
    WORK("仕事"),
    SHOPPING("買い物"),
    OTHERS("その他");
}

fun Gategory.getName() = when(this.name){
    Gategory.PERSONAL.name -> "個人"
    Gategory.WORK.name -> "仕事"
    Gategory.SHOPPING.name ->"買い物"
    Gategory.OTHERS.name ->"その他"
    else ->""
}


