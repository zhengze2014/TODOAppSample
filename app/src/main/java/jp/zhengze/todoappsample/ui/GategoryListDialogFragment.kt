package jp.zhengze.todoappsample.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import jp.zhengze.todoappsample.data.Gategory
import jp.zhengze.todoappsample.data.getName


class GategoryListDialogFragment : DialogFragment() {

    lateinit var callback: Callback

    interface Callback {
        fun onSelectGategory(gategory: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = targetFragment as TodoFragment
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val items = arrayOf<CharSequence>(
            Gategory.PERSONAL.getName(),
            Gategory.WORK.getName(),
            Gategory.SHOPPING.getName(),
            Gategory.OTHERS.getName()
        )


        val builder = AlertDialog.Builder(context!!)
        builder.setItems(items) { _, index ->
            callback.onSelectGategory(items[index].toString())
        }
        return builder.create()
    }


}

