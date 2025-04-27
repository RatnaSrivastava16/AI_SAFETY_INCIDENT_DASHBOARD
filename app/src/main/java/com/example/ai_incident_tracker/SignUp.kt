package com.example.ai_incident_tracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ai_incident_tracker.ui.incidentlist.IncidentListActivity

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        var signup = findViewById<Button>(R.id.signup)
        var login=findViewById<TextView>(R.id.textView5)
        signup.setOnClickListener {
            var name = findViewById<EditText>(R.id.editTextTextName).text.toString()
            var email = findViewById<EditText>(R.id.editTextText).text.toString()
            var password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            var sharedPref =getSharedPreferences("My Details", Context.MODE_PRIVATE)
            var editor = sharedPref.edit()
            if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                editor.putString("name", name)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()
                var intent = Intent(this, IncidentListActivity::class.java)
                startActivity(intent);
            }
            else{
                showCustomToast("Please fill in all the credentials.")
            }
        }
        login.setOnClickListener{
            var intent=Intent(this,SignIn::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.toastContainer))

        // Set the message to the TextView inside the custom toast
        val toastMessage: TextView = layout.findViewById(R.id.toastMessage)
        toastMessage.text = message

        // Create and show the custom Toast
        val customToast = Toast(applicationContext)
        customToast.duration = Toast.LENGTH_SHORT
        customToast.view = layout
        customToast.setGravity(android.view.Gravity.CENTER, 0, 0)
        customToast.show()
    }

}