package sk.itsovy.android.voting

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Constraints

// overit ci vytiahne dobre meno z preferences

class VotingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)

        val pref = getPreferences(Context.MODE_PRIVATE)
        val name = pref.getString("name", "UNKNOWN")
        val textView = findViewById<TextView>(R.id.name)
        textView.text = name

        val list = mutableListOf<Button>()
        for (i in 'A'..'D') {
            val button = Button(this)
            with(button) {
                id = View.generateViewId()
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                text = "choice $i"
                constraintLayout.addView(this)
                list.add(this)
            }
        }


        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        // uchytim kazdy button vlavo a vpravo
        for (button in list) {
            constraintSet.connect(
                button.id, ConstraintSet.START, constraintLayout.id, ConstraintSet.START
            )
            constraintSet.connect(
                button.id, ConstraintSet.END, constraintLayout.id, ConstraintSet.END
            )
        }
        // prvy button uchytit na textview
        constraintSet.connect(list[0].id, ConstraintSet.TOP, textView.id, ConstraintSet.BOTTOM)

        // i-ty button na i-1
        list.zipWithNext { buttonA, buttonB ->
            constraintSet.connect(buttonB.id, ConstraintSet.TOP, buttonA.id, ConstraintSet.BOTTOM)
        }

        constraintSet.applyTo(constraintLayout)

    }
}