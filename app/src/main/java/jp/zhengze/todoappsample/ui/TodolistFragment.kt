package jp.zhengze.todoappsample.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.zhengze.todoappsample.R
import jp.zhengze.todoappsample.data.*
import jp.zhengze.todoappsample.databinding.FragmentTodolistBinding
import jp.zhengze.todoappsample.databinding.ItemHeadBinding
import jp.zhengze.todoappsample.databinding.ItemTodoBinding

class TodolistFragment : Fragment() {


    val tododataModel = TodoDataModel.creatTodoDataModel()

    private lateinit var binding: FragmentTodolistBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodolistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.todoFragment)
        }

    }

    private fun setupRecyclerview() {
        binding.recyvler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerAdapter(context, tododataModel.datas)
        }
    }
}

class RecyclerAdapter(private val context: Context, private val datas: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return when (datas[position]) {
            is HeadItem -> TYPE_GATEGORY
            is TodoItem -> TYPE_TODO
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        if (viewType == TYPE_GATEGORY) {
            return HeadViewHolder(ItemHeadBinding.inflate(layoutInflater, parent, false))
        }
        return TodoViewHolder(ItemTodoBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodoViewHolder) {
            (datas[position] as TodoItem).apply {
                holder.binding.date = this.date
                holder.binding.todo = this.todo
            }
        } else if (holder is HeadViewHolder) {
            (datas[position] as HeadItem).apply {
                holder.binding.category = this.gategory.getName()
            }
        }
    }

    companion object {
        private val TYPE_GATEGORY = 1
        private val TYPE_TODO = 2

        class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

        class HeadViewHolder(val binding: ItemHeadBinding) : RecyclerView.ViewHolder(binding.root)
    }

}
