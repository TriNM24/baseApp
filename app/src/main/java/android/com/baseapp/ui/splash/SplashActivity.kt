package android.com.baseapp.ui.splash

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
        viewModel.resultChecking.observe(this){
            when(it){
                ResultSplash.LOGIN -> {
                    Toast.makeText(baseContext,"Login",Toast.LENGTH_SHORT).show()
                }
                ResultSplash.MAIN -> {
                    Toast.makeText(baseContext,"Main",Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.processChecking()
    }
}