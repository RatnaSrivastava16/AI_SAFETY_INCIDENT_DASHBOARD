package com.example.ai_incident_tracker.data

// Incident.kt
data class Incident(
    val id: Int,
    val title: String,
    val description: String,
    val severity: String,
    val reported_at: String
)