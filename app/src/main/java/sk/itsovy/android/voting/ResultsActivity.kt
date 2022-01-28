package sk.itsovy.android.voting

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val textView = findViewById<TextView>(R.id.textView)


        val pref = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        var name = pref.getString("name", null)
        if (name == null) {
            // tu by mal byt alert dialog
            name = "M.O."
            with(pref.edit()) {
                putString("name", name)
                apply()
            }
        }


        if (intent == null || intent.extras == null) {
            // spustila sa aplikacia
            val intent = Intent(this, VotingActivity::class.java)
            startActivity(intent)
        } else {
            // niekto hlasoval vo voting aktivite
            val choice = intent.extras!!.getString("choice")
            val viewModel: CommunicationViewModel by viewModels()
            // name ani choice null nebude
            viewModel.sendVote(name, choice!!)

            viewModel.votes.observe(this) {
                textView.text = it.toString()
            }
            val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
            refreshLayout.setOnRefreshListener {
                viewModel.getVotes()
                refreshLayout.isRefreshing = false
            }

        }

    }
}