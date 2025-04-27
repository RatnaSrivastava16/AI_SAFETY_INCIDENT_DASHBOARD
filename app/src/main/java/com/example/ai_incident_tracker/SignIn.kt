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

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        val signup = findViewById<TextView>(R.id.textView5)
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextText).text.toString()
            val loginpassword = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

            // Check if username and password are provided
            if (email.isNotEmpty() && loginpassword.isNotEmpty()) {
                val sharedPref = getSharedPreferences("My Details", Context.MODE_PRIVATE)
                val name = sharedPref.getString("name", "")
                val password = sharedPref.getString("password", "")

                if (name.isNullOrEmpty() || password.isNullOrEmpty()) {
                    showCustomToast("Please sign up first")
                    navigateToSignUp() // Redirect to SignUp Fragment
                    return@setOnClickListener
                }
                if (email == email && loginpassword == password) {
                    val intent = Intent(this, IncidentListActivity::class.java)
                    startActivity(intent)
                    showCustomToast("Login successful!")
                } else {
                    showCustomToast("Invalid Login Details")
                }
            } else {
                showCustomToast("Please enter login details")
            }
        }

        signup.setOnClickListener {
            navigateToSignUp()
        }
    }
    private fun navigateToSignUp() {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.toastContainer))

        val toastMessage: TextView = layout.findViewById(R.id.toastMessage)
        toastMessage.text = message

        val customToast = Toast(applicationContext)
        customToast.duration = Toast.LENGTH_SHORT
        customToast.view = layout
        customToast.setGravity(android.view.Gravity.CENTER, 0, 0)
        customToast.show()
    }
}