package wayne.com.userchallenge.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_user.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import wayne.com.userchallenge.R
import wayne.com.userchallenge.data.User
import wayne.com.userchallenge.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), IMainActivity {

    private val mainViewModel: MainViewModel by viewModel()
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.mainActivity = this
        mainViewModel.getUsers()
        users_progress.visibility = View.VISIBLE


        user_rec_view.layoutManager = GridLayoutManager(this, 2)
        user_rec_view.adapter = adapter

        val alertDialog: AlertDialog? = getAlertDialog()

        floatingActionButton.setOnClickListener{ view ->
            alertDialog?.show()
        }


    }

    private fun getAlertDialog() : AlertDialog {
        return this.let {
            val builder = AlertDialog.Builder(it)
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.new_user, null)
            val nameEditText = dialogView.name
            val jobEditText = dialogView.job
            builder.setView(dialogView)
                .setPositiveButton(
                    R.string.ok
                ) { dialog, _ ->
                    mainViewModel.postUser(name = nameEditText.text.toString(), job = jobEditText.text.toString())
                    dialog.dismiss()
                }
                .setNegativeButton(
                    R.string.cancel
                ) { dialog, _ ->
                    dialog.cancel()
                }
                .create()

        }
    }

    override fun setList(users: List<User>) {
        adapter.setList(users)
    }

    override fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun removeSpinner(){
        users_progress.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.dispose()
    }
}
