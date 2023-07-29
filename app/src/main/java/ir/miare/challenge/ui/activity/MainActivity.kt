package ir.miare.challenge.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.NavHostFragment
import ir.miare.challenge.R
import ir.miare.challenge.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment

        val inflater = navHost.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_nav_graph)
        navController = navHost.navController
        navController.graph = graph
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
