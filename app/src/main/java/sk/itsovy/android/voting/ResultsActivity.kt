package sk.itsovy.android.voting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val intent = Intent(this, VotingActivity::class.java)
        startActivity(intent)
    }
}