package wayne.com.userchallenge.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import wayne.com.userchallenge.R
import wayne.com.userchallenge.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()
    val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.adapter = adapter
        mainViewModel.getUsers()


        user_rec_view.layoutManager = LinearLayoutManager(this)
        user_rec_view.adapter = adapter

    }
}
