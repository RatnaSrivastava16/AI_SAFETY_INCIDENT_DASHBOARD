package com.example.ai_incident_tracker.data

// Incident.kt
data class Incident(
    val id: Int,
    val title: String,
    val description: String,
    val severity: String, // "Low", "Medium", "High", "Critical"
    val reported_at: String // ISO 8601 format
)