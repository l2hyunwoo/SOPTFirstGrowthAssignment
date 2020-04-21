package com.example.firstgassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_register.setOnClickListener{
            if(et_mail.text.isNullOrBlank() || et_password.text.isNullOrBlank() || et_passwordCheck.text.isNullOrBlank()|| et_gitNick.text.isNullOrBlank()){
                Toast.makeText(this, "아이디, 패스워드, 패스워드 확인 창을 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(et_password.text.toString() != et_passwordCheck.text.toString()){
                Toast.makeText(this, "패스워드와 패스워드 확인에 있는 내용이 같은 지 확인하세요", Toast.LENGTH_SHORT).show()
            }
            else{
                var stringDatas = arrayOf("${et_mail.text.toString()}", "${et_password.text.toString()}")
//                Log.d("datas[1]", "${stringDatas[0]}")
//                Log.d("datas[1]", "${stringDatas[1]}")
                MySharedPreferences.myEtMail = et_mail.text.toString()
                MySharedPreferences.myEtPassword = et_password.text.toString()
                val intent = Intent().apply{
                    putExtra("datas", stringDatas)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
