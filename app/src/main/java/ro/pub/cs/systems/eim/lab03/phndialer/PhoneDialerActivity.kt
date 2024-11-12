package ro.pub.cs.systems.eim.lab03.phndialer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PhoneDialerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val phoneNumberInput = findViewById<EditText>(R.id.phoneNumberInput)

        // List of button IDs and their corresponding numbers
        val buttonIdToNumber = mapOf(
            R.id.button1 to "1",
            R.id.button2 to "2",
            R.id.button3 to "3",
            R.id.button4 to "4",
            R.id.button5 to "5",
            R.id.button6 to "6",
            R.id.button7 to "7",
            R.id.button8 to "8",
            R.id.button9 to "9"
        )

        // Set up listeners for each button
        for ((buttonId, number) in buttonIdToNumber) {
            findViewById<Button>(buttonId).setOnClickListener {
                // Append the number to the EditText
                phoneNumberInput.append(number)
            }
        }

        findViewById<Button>(R.id.buttonCall).setOnClickListener {
            val phoneNumber = phoneNumberInput.text.toString()
            if (phoneNumber.isNotEmpty()) {
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                startActivity(callIntent)
            }
        }

        // Set up listener for the "Exit" button
        findViewById<Button>(R.id.buttonExit).setOnClickListener {
            finish() // This will close the current activity (and exit the app if it's the only activity)
        }
    }
}