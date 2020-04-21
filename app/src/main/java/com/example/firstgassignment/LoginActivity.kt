package com.example.firstgassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_clogin.*

class LoginActivity : AppCompatActivity() {
    private val REGISTER_ACTIVITY_REQUEST_CODE = 0
    private var password:String = MySharedPreferences.myEtPassword
    private var idAdress:String = MySharedPreferences.myEtMail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clogin)
        if(MySharedPreferences.myIsLogin){
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "자동로그인입니다.", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        button.setOnClickListener {
                if(et_mail.text.isNullOrBlank() || et_password.text.isNullOrBlank()){
                    Toast.makeText(this, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                }else{
                    if(password.isNullOrBlank() || idAdress.isNullOrBlank()) {
                        Toast.makeText(this, "회원가입하셔서 회원정보 만들어 오세요", Toast.LENGTH_SHORT).show()
                    }
                    else if(et_mail.text.toString() == idAdress && et_password.text.toString() == password){
                        MySharedPreferences.myIsLogin = true

                        val intent = Intent(this, MainActivity::class.java)
                        Toast.makeText(this, "로그인입니다. 환영해요", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "아이디와 비밀번호를 다시 확인하세요.", Toast.LENGTH_SHORT).show()
                    }

                }


        }
        tv_register.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, REGISTER_ACTIVITY_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REGISTER_ACTIVITY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val datas = data!!.getStringArrayExtra("datas")
                password = datas[1]
                idAdress = datas[0]
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
