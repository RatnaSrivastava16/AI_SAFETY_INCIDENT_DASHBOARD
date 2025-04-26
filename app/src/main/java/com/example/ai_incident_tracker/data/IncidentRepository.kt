package com.example.ai_incident_tracker.data

// IncidentRepository.kt
object IncidentRepository {
    private val incidents = mutableListOf(
        Incident(
            1,
            "Biased Recommendation Algorithm",
            "Algorithm consistently favored products from certain demographics over others.",
            "Medium",
            "2025-03-15T10:00:00Z"
        ),
        Incident(
            2,
            "Chatbot Generating Harmful Content",
            "AI chatbot started generating violent content when prompted with specific keywords.",
            "High",
            "2025-03-20T14:30:00Z"
        ),
        Incident(
            3,
            "Autonomous Vehicle Misclassification",
            "Self-driving car failed to recognize pedestrians in low-light conditions.",
            "Critical",
            "2025-04-01T08:15:00Z"
        ),
        Incident(
            4,
            "Facial Recognition Bias",
            "System showed significantly lower accuracy for certain ethnic groups.",
            "Medium",
            "2025-04-05T11:20:00Z"
        ),
        Incident(
            5,
            "Data Leakage in ML Model",
            "Model was found to be memorizing and potentially revealing sensitive training data.",
            "High",
            "2025-04-10T09:45:00Z"
        ),
        Incident(
            6,
            "Medical Diagnosis System Failure",
            "AI diagnostic tool provided incorrect cancer risk assessments for female patients.",
            "Critical",
            "2025-04-12T07:30:00Z"
        ),
        Incident(
            7,
            "Financial Algorithm Flash Crash",
            "Trading algorithm caused 5% market dip due to feedback loop in liquidity analysis.",
            "High",
            "2025-04-14T09:15:00Z"
        ),
        Incident(
            8,
            "Voice Assistant Privacy Breach",
            "System recorded and stored conversations without proper user consent.",
            "Medium",
            "2025-04-16T13:45:00Z"
        ),
        Incident(
            9,
            "Content Moderation Overblocking",
            "AI flagged 32% of legitimate posts as false positives in political discourse.",
            "Low",
            "2025-04-18T16:20:00Z"
        ),
        Incident(
            10,
            "Predictive Policing Bias",
            "Crime prediction system disproportionately targeted minority neighborhoods.",
            "High",
            "2025-04-20T11:10:00Z"
        ),
        Incident(
            11,
            "Deepfake Authentication Bypass",
            "Facial recognition system was fooled by high-quality deepfake video.",
            "Critical",
            "2025-04-22T14:55:00Z"
        ),
        Incident(
            12,
            "Language Model Hallucinations",
            "Chatbot provided dangerous medical advice with 90% confidence.",
            "High",
            "2025-04-24T10:05:00Z"
        ),
        Incident(
            13,
            "Autonomous Weapon Test Failure",
            "Drone misidentified civilian vehicles as military targets during testing.",
            "Critical",
            "2025-04-25T18:30:00Z"
        ),
        Incident(
            14,
            "HR Screening Algorithm Discrimination",
            "Resume screening tool penalized applicants from women's colleges.",
            "Medium",
            "2025-04-27T09:40:00Z"
        ),
        Incident(
            15,
            "Social Media Echo Chamber Amplification",
            "Recommendation engine increased political polarization metrics by 27%.",
            "Low",
            "2025-04-28T15:15:00Z"
        ),
        Incident(
            16,
            "AI-Generated Fake Reviews",
            "System detected generating 5,000+ fake product reviews per day.",
            "Medium",
            "2025-04-29T12:25:00Z"
        ),
        Incident(
            17,
            "Emergency Response System Delay",
            "911 dispatch AI prioritized calls based on flawed socioeconomic metrics.",
            "High",
            "2025-04-30T07:50:00Z"
        ),
        Incident(
            18,
            "Autonomous Delivery Route Bias",
            "Service avoided neighborhoods with certain demographic profiles.",
            "Medium",
            "2025-05-01T14:10:00Z"
        ),
        Incident(
            19,
            "AI-Assisted Grading Errors",
            "Automated test scoring system misgraded 12% of essays.",
            "Low",
            "2025-05-02T11:30:00Z"
        ),
        Incident(
            20,
            "Predictive Maintenance False Alerts",
            "Industrial IoT system caused unnecessary shutdowns due to sensor misinterpretation.",
            "Medium",
            "2025-05-03T16:45:00Z"
        )
    )

    fun getIncidents(): List<Incident> = incidents.toList()
    fun getFilteredIncidents(severity: String?): List<Incident> {
        return if (severity.isNullOrEmpty()) {
            incidents.toList()
        } else {
            incidents.filter { it.severity == severity }
        }
    }
    fun addIncident(incident: Incident) {
        incidents.add(incident)
    }
}