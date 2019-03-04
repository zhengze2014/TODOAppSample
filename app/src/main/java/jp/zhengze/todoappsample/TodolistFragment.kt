package jp.zhengze.todoappsample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.zhengze.todoappsample.databinding.FragmentTodolistBinding
import jp.zhengze.todoappsample.databinding.ItemTodoBinding

class TodolistFragment : Fragment() {

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
            adapter = RecyclerAdapter(context, listOf("999"))
        }
    }
}

class RecyclerAdapter(private val context: Context, private val todoList: List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.Companion.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.Companion.MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemTodoBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.category = "iii"
        holder.binding.todo = "bbbbbb"
        holder.binding.date = "ccccc"
    }

    companion object {
        class MyViewHolder(var binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)
    }

}
