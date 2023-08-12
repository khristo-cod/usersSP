package com.example.userssp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), onClickListener {

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}", Toast.LENGTH_SHORT).show()
    }

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }

        val swipeHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter.remove(viewHolder.adapterPosition)
            }
        })
        swipeHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val diego = User(1, "Diego", "Alfonso", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJHSclzQ40t_4ZIhnxIy9Rwf-_MlKSi5iXIzRhh0qgOFju6sX-qIDpNWjrvcmLX6Cgci8&usqp=CAU")
        val daniel = User(2, "Daniel", "Obed", "data:image/jpeg;base64,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")
        val crist = User(3, "Cristialvi","Aponte", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAyet5a5aUKUoy8n1i8kq2C5Q2OGvi7EnqMg&usqp=CAU")

        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        users.add(daniel)
        users.add(diego)
        users.add(crist)
        return users
    }
}