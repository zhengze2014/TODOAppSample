package jp.zhengze.todoappsample.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import jp.zhengze.todoappsample.data.*
import jp.zhengze.todoappsample.databinding.FragmentTodoBinding
import java.util.*


class TodoFragment() : Fragment(), GategoryListDialogFragment.Callback {

    var addGategory = Gategory.OTHERS
    override fun onSelectGategory(gategory: String) {
        binding.gategory = gategory
    }

    private lateinit var binding: FragmentTodoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
        binding.dateTextview.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    binding.date = year.toString() + "年" + (month + 1).toString() + "月" + day.toString() + "日"
                },
                year,
                month,
                dayOfMonth
            ).show()
        }

        binding.gategoryTextview.setOnClickListener {
            val gategoryListDialogFragment = GategoryListDialogFragment()
            gategoryListDialogFragment.setTargetFragment(this@TodoFragment,
                REUEST_CODDE
            )
            gategoryListDialogFragment.show(fragmentManager, "gategory")
        }

        binding.fab.setOnClickListener {
            if(binding.gategory == null){
                AlertDialog.Builder(context!!).setMessage("カテゴリを選んてない？").show()
                return@setOnClickListener
            }
            if(binding.titleTodo.text.toString().isNullOrEmpty()){
                AlertDialog.Builder(context!!).setMessage("タイトル欄に入力されてない").show()
                return@setOnClickListener
            }
            TodoDataModel.creatTodoDataModel().insertTodoData(TodoWithGategory(binding.date, binding.titleTodo.text.toString(), getGategory(binding.gategory)))
            it.findNavController().popBackStack()
        }
    }

    fun getGategory(string: String?) =when(string){
        "個人" -> Gategory.PERSONAL
        "買い物" -> Gategory.SHOPPING
        "仕事" -> Gategory.SHOPPING
        "その他" -> Gategory.OTHERS
        else -> null
    }

    companion object {
        val REUEST_CODDE = 0
    }

}

