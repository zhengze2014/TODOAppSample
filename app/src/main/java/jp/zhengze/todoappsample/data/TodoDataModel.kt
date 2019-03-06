package jp.zhengze.todoappsample.data

class TodoDataModel private constructor(){

    val todoWithGategoryList = mutableListOf<TodoWithGategory>()
    val datas = mutableListOf<Item>()

    fun insertTodoData(todoWithGategory: TodoWithGategory) {
        todoWithGategoryList.add(todoWithGategory)
        datas.clear()
        setupData(Gategory.PERSONAL)
        setupData(Gategory.WORK)
        setupData(Gategory.SHOPPING)
        setupData(Gategory.OTHERS)
    }

    private fun setupData(gategory: Gategory){
        todoWithGategoryList.filter {
            it.gategory == gategory
        }.map {
                if (it.date == null) {
                    TodoItem(date = " ",todo = it.todo)
                }else{
                    TodoItem(date = it.date,todo = it.todo)

                }
        }.apply {
            if(this.isEmpty())return@apply
            datas.add(HeadItem(gategory))
            datas.addAll(this)
        }
        return
    }

    companion object {
        private  val todoDataModel = TodoDataModel()
        fun creatTodoDataModel() = todoDataModel
    }
}
