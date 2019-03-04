package jp.zhengze.todoappsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import jp.zhengze.todoappsample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val binding by lazy{ DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding
    }

    override fun onSupportNavigateUp()= findNavController(this, R.id.my_nav_host_fragment).navigateUp()


}
