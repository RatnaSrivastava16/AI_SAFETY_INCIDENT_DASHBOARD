package com.example.ai_incident_tracker.ui.incidentlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_incident_tracker.R
import com.example.ai_incident_tracker.data.Incident
import com.example.ai_incident_tracker.data.IncidentRepository
import com.example.ai_incident_tracker.ui.incidentdetail.IncidentDetailActivity
import com.example.ai_incident_tracker.ui.reportincident.ReportIncidentActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IncidentListActivity : AppCompatActivity() {
    private lateinit var adapter: IncidentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var filterChipGroup: ChipGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "AI Safety Incidents"
        recyclerView = findViewById(R.id.incident_recycler_view)
        filterChipGroup = findViewById(R.id.filter_chip_group)
        adapter = IncidentAdapter { incident -> onIncidentClicked(incident) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        setupFilterChips()
        loadIncidents()
        findViewById<FloatingActionButton>(R.id.fab_add_incident).setOnClickListener {
            startActivity(Intent(this, ReportIncidentActivity::class.java))
        }
    }
    private fun setupFilterChips() {
        val severities = listOf("All", "Low", "Medium", "High", "Critical")
        severities.forEach { severity ->
            val chip = Chip(this).apply {
                text = severity
                isCheckable = true
                if (severity == "All") {
                    isChecked = true
                }
                setOnClickListener { loadIncidents(if (severity == "All") null else severity) }
            }
            filterChipGroup.addView(chip)
        }
    }

    private fun loadIncidents(severity: String? = null) {
        val incidents = if (severity == null || severity == "All") {
            IncidentRepository.getIncidents()
        } else {
            IncidentRepository.getFilteredIncidents(severity)
        }
        adapter.submitList(incidents)
    }

    private fun onIncidentClicked(incident: Incident) {
        val intent = Intent(this, IncidentDetailActivity::class.java).apply {
            putExtra("INCIDENT_ID", incident.id)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        loadIncidents()
    }
}