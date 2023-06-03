package com.example.gymbuddy


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var userListView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDao = AppDatabase.getDatabase(this).userDao()

        userListView = findViewById(R.id.user_listview)

        adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayList<String>()) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.gravity = Gravity.CENTER
                return view
            }
        }

        userListView.adapter = adapter

        userDao.getUserNames().observe(this, Observer { userNames ->
            adapter.clear()
            adapter.addAll(userNames)
        })

        //przeniesienie do nowego okienka po wcisnieciu imienia
        userListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val selectedUserName = adapter.getItem(position)!!

            userDao.getUserByName(selectedUserName).observe(this, Observer { user ->
                val intent = Intent(this, UserMenu::class.java)
                intent.putExtra("userId", user.id)
                startActivity(intent)
            })
        }

        // Obsługa kliknięcia przycisku "Dodaj użytkownika"
        val create_profile_button = findViewById<Button>(R.id.add_user_button)

        create_profile_button.setOnClickListener {
            val intent = Intent(this, NewUserForm::class.java)
            startActivity(intent)
        }
    }
}