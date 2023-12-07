package com.example.studify.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studify.R
import com.example.studify.data.UserResponse
import com.example.studify.databinding.ActivityLoginBinding
import com.example.studify.databinding.ActivityRegisterBinding
import com.example.studify.network.APIClient
import com.example.studify.network.APIInterface
import com.example.studify.ui.home.HomeActivity
import com.example.studify.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var apiInterface: APIInterface
    private lateinit var userResponseCall: Call<UserResponse>

    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiInterface = APIClient.getRetrofit(this)!!.create(APIInterface::class.java)

        binding.BTNregis.setOnClickListener {
            if (binding.ETnamaregis.text.isEmpty() || binding.ETemailregis.text.isEmpty() || binding.ETpassregis.text.isEmpty()) {
                Toast.makeText(this, "Data Masih Kosong", Toast.LENGTH_SHORT).show()
            } else {
                userResponseCall = apiInterface.register(
                    binding.ETnamaregis.text.toString(),
                    binding.ETemailregis.text.toString(),
                    binding.ETpassregis.text.toString()
                )
                userResponseCall.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        if (response.body()!!.msg.equals("Berhasil")) {
                            startActivity(Intent(applicationContext, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                response.body()!!.msg.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "No internet Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
        }
        binding.TVRegis.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }
    }
}