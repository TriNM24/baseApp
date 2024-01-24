package android.com.baseapp.ui.splash

import android.com.baseapp.MainActivity
import android.com.baseapp.R
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        viewModel.resultChecking.observe(this){
            when(it){
                ResultSplash.LOGIN -> {
                    Toast.makeText(baseContext,"Login",Toast.LENGTH_SHORT).show()
                }
                ResultSplash.MAIN -> {
                    val intent = Intent(this, MainActivity::class.java)
                    val bundle = Bundle()
                    this.startActivity(intent, bundle)
                }
            }
        }
        viewModel.processChecking()
    }
}