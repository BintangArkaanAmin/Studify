package com.example.studify.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.studify.R
import com.example.studify.data.UserResponse
import com.example.studify.databinding.ActivityLoginBinding
import com.example.studify.network.APIClient
import com.example.studify.network.APIInterface
import com.example.studify.ui.home.HomeActivity
import com.example.studify.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var apiInterface: APIInterface
    private lateinit var userResponseCall: Call<UserResponse>

    private lateinit var binding: ActivityLoginBinding

    lateinit var tvlogin : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiInterface = APIClient.getRetrofit(this)!!.create(APIInterface::class.java)

        binding.BTNlogin.setOnClickListener {
            if (binding.ETemaillogin.text.isEmpty()|| binding.ETpasslogin.text.isEmpty()){
                Toast.makeText(this, "Data Masih Kosong", Toast.LENGTH_SHORT).show()
            }else{
                userResponseCall = apiInterface.login(binding.ETemaillogin.text.toString(),binding.ETpasslogin.text.toString())
                userResponseCall.enqueue(object : Callback<UserResponse>{
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        if (response.body()!!.msg.equals("Berhasil")){
                            startActivity(Intent(applicationContext, HomeActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity, response.body()!!.msg.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "No internet Connection", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        binding.TVLogin.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }
    }
}