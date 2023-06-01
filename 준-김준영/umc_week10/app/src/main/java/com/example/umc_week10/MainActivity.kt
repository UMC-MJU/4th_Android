package com.example.umc_week10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_week10.databinding.ActivityInformBinding
import com.example.umc_week10.databinding.ActivityMainBinding
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivNaver.setOnClickListener {
            val OAuthLoginCallback = object: OAuthLoginCallback{
                override fun onSuccess() {
                    NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse>{
                        override fun onSuccess(result: NidProfileResponse) {
                            val intent = Intent(this@MainActivity, InformAcitivity::class.java)
                            intent.putExtra("name", result.profile?.name.toString())
                            intent.putExtra("email", result.profile?.email.toString())
                            startActivity(intent)
                        }

                        override fun onError(errorCode: Int, message: String) {


                        }

                        override fun onFailure(httpStatus: Int, message: String) {

                        }
                    })
                }

                override fun onFailure(httpStatus: Int, message: String) {

                }

                override fun onError(errorCode: Int, message: String) {
                    val naverAccessToken = NaverIdLoginSDK.getAccessToken()
                    Log.e("TAG", "naverACcessToken : $naverAccessToken")
                }
            }
            NaverIdLoginSDK.initialize(this@MainActivity, getString(R.string.naver_client_id), getString(R.string.naver_client_secret), "UMC")
            NaverIdLoginSDK.authenticate(this@MainActivity, OAuthLoginCallback)
        }
    }
}