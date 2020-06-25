package by.agd.rsandroidtask4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import by.agd.rsandroidtask4.database.CarDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navController = findNavController(R.id.fragment_container)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
        CarDatabase.init(applicationContext)
    }
}