package com.example.ai_incident_tracker.ui.incidentlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_incident_tracker.R
import com.example.ai_incident_tracker.data.Incident
import java.text.SimpleDateFormat
import java.util.Locale

class IncidentAdapter(private val onClick: (Incident) -> Unit) :
    ListAdapter<Incident, IncidentAdapter.IncidentViewHolder>(IncidentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class IncidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.incident_title)
        private val severityTextView: TextView = itemView.findViewById(R.id.incident_severity)
        private val dateTextView: TextView = itemView.findViewById(R.id.incident_date)
        private val cardView: CardView = itemView.findViewById(R.id.incident_card)

        fun bind(incident: Incident) {
            titleTextView.text = incident.title
            severityTextView.text = incident.severity

            // Format date string
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            val parsedDate = inputFormat.parse(incident.reported_at)
            dateTextView.text = parsedDate?.let { outputFormat.format(it) } ?: "Invalid date"

            // Color based on severity
            val (bgColorRes, textColorRes) = when (incident.severity) {
                "Critical" -> Pair(R.color.severity_critical, R.color.white)
                "High" -> Pair(R.color.severity_high, R.color.white)
                "Medium" -> Pair(R.color.severity_medium, R.color.black)
                else -> Pair(R.color.severity_low, R.color.black)
            }

            severityTextView.setBackgroundColor(ContextCompat.getColor(itemView.context, bgColorRes))
            severityTextView.setTextColor(ContextCompat.getColor(itemView.context, textColorRes))

            cardView.setOnClickListener {
                onClick(incident)
            }
        }
    }
}

class IncidentDiffCallback : DiffUtil.ItemCallback<Incident>() {
    override fun areItemsTheSame(oldItem: Incident, newItem: Incident): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Incident, newItem: Incident): Boolean {
        return oldItem == newItem
    }
}
