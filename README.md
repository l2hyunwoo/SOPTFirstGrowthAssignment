# SOPT Android 필수과제 2번 및 성장과제
SOPT 안드로이드 필수과제 2

## 액티비티간 데이터 전달, sAFR && oAR
### LoginActivity.kt
```
tv_register.setOnClickListener{
    val intent = Intent(this, [실행시키고 싶은 액티비티])
    startActivityForResult(intent, REGISTER_ACTIVITY_REQUEST_CODE)
}
```
```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if(requestCode == '무언가'){
        if(resultCode == '무언가'){
            대략적으로 다른 액티비티에서 데이터를 받아오겠다는 뜻
        }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

}
```

### RegisterActivity.kt
```
btn_register.setOnClickListener{
    //중략
    else{
        var stringDatas = arrayOf("${et_mail.text.toString()}", "${et_password.text.toString()}")
        val intent = Intent().apply{
            putExtra("datas", stringDatas)
        }
        setResult(Activity.RESULT_OK, intent) //데이터 설정
        finish()                              //버튼 누르고 데이터 입력한 다음 창 종료
    }
}
```

위와 같은 방법으로 데이터들을 주고받을 수가 있었다.

## 로그인 유지를 SharedReference(이하 SR)로 한다
### LoginActivity.kt
+ object class MySharedReference를 만들고 해당 클래스에 isLogin, mail, password에 대한 정보를 담을 수 있게 구현
+ 회원가입 후 로그인 성공 시 isLogin을 true로 바꾸고 회원가입 정보(mail, password) 역시 SR에 저장하여 앱 종료뒤에도 자동 로그인할 수 있게 구현
+ mainActivity가 종료되어서 로그인 창으로 가도 SR에 저장된 데이터로 로그인을 할 수 있게 구현

```
if(MySharedPreferences.myIsLogin){
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "자동로그인입니다.", Toast.LENGTH_SHORT).show()
            startActivity(intent)
}
```
```
button.setOnClickListener {
//중략
	else if(et_mail.text.toString() == idAdress && et_password.text.toString() == password){
		MySharedPreferences.myIsLogin = true
		val intent = Intent(this, MainActivity::class.java)
		Toast.makeText(this, "로그인입니다. 환영해요", Toast.LENGTH_SHORT).show()
		startActivity(intent)
	}
}
```

### RegisterActivity.kt
```
MySharedPreferences.메일정보 = et_mail.text.toString()
MySharedPreferences.비밀번호정보 = et_password.text.toString()
```
