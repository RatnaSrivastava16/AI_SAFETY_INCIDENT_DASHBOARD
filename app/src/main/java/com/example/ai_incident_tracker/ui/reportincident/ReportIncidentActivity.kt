package com.example.ai_incident_tracker.ui.reportincident

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ai_incident_tracker.R
import com.example.ai_incident_tracker.data.Incident
import com.example.ai_incident_tracker.data.IncidentRepository
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReportIncidentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_incident)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Report New Incident"

        val severitySpinner = findViewById<Spinner>(R.id.severity_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.severity_levels,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            severitySpinner.adapter = adapter
        }

        findViewById<Button>(R.id.save_button).setOnClickListener{
            val title = findViewById<EditText>(R.id.title_edit_text).text.toString().trim()
            val description = findViewById<EditText>(R.id.description_edit_text).text.toString().trim()
            val severity = severitySpinner.selectedItem.toString()

            if (validateInput(title, description)) {
                val newIncident = Incident(
                    id = IncidentRepository.getIncidents().maxOf { it.id } + 1,
                    title = title.toString(),
                    description = description,
                    severity = severity,
                    reported_at = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                        .format(Date())
                )

                IncidentRepository.addIncident(newIncident)
                Toast.makeText(this, "Incident reported successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun validateInput(title: String, description: String): Boolean {
        return when {
            title.isEmpty() -> {
                findViewById<TextInputLayout>(R.id.title_input_layout).error = "Title is required"
                false
            }
            description.isEmpty() -> {
                findViewById<TextInputLayout>(R.id.title_input_layout).error = null
                findViewById<TextInputLayout>(R.id.description_input_layout).error = "Description is required"
                false
            }
            else -> {
                findViewById<TextInputLayout>(R.id.title_input_layout).error = null
                findViewById<TextInputLayout>(R.id.description_input_layout).error = null
                true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}