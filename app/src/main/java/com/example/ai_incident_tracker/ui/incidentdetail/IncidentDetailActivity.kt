package com.example.ai_incident_tracker.ui.incidentdetail

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ai_incident_tracker.R
import com.example.ai_incident_tracker.data.IncidentRepository
import java.text.SimpleDateFormat
import java.util.Locale

class IncidentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident_detail)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val incidentId = intent.getIntExtra("INCIDENT_ID", -1)
        val incident = IncidentRepository.getIncidents().find { it.id == incidentId }

        incident?.let {
            findViewById<TextView>(R.id.detail_title).text = it.title
            findViewById<TextView>(R.id.detail_severity).text = it.severity
            findViewById<TextView>(R.id.detail_description).text = it.description

            val parsedDate = try {
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(it.reported_at)
            } catch (e: Exception) {
                null
            }

            val formattedDate = parsedDate?.let { date ->
                SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", Locale.getDefault()).format(date)
            } ?: "Unknown Date"

            findViewById<TextView>(R.id.detail_date).text = formattedDate

            // Set severity color
            val severityView = findViewById<TextView>(R.id.detail_severity)
            val colorRes = when (it.severity) {
                "Critical" -> R.color.severity_critical
                "High" -> R.color.severity_high
                "Medium" -> R.color.severity_medium
                else -> R.color.severity_low
            }
            severityView.setBackgroundColor(ContextCompat.getColor(this, colorRes))
            severityView.setTextColor(
                if (it.severity == "Critical" || it.severity == "High")
                    ContextCompat.getColor(this, R.color.white)
                else
                    ContextCompat.getColor(this, R.color.black)
            )
        } ?: run {
            Toast.makeText(this, "Incident not found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
