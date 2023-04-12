package com.example.umc_week4_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.umc_week4_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewbinding: ActivityMainBinding
    var message:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        val messageEdit:EditText = viewbinding.txtEdit
        val messageBtn:Button = viewbinding.btnMain
         //variable to receive a message

        messageBtn.isEnabled = false

        messageEdit.addTextChangedListener(object:TextWatcher{ //텍스트 변화를 감지하는 함수
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int,p3: Int){}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int,p3: Int){ //텍스트가 변경되는 순간 호출됨
                message = messageEdit.text.toString()
                messageBtn.isEnabled = message.isNotEmpty() //데이터를 할당받는다면 True
            }
            override fun afterTextChanged(p0:Editable?) {}
            })


        messageBtn.setOnClickListener{
            viewbinding.txtMain.text = message
        }
    }

    private var savedMesaage:String = ""

    override fun onPause() {
        super.onPause()
        savedMesaage = viewbinding.txtEdit.text.toString() //뒤로가기 버튼이나 다른 예외상황이 발생할 때 입력했던 데이터를 저장하는 변수
        Toast.makeText(this,"앱이 일시정지됩니다",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        if(savedMesaage.isNotEmpty()){
            message = savedMesaage
            viewbinding.txtEdit.setText(message) //마지막으로 입력했던 데이터 할당받기
            viewbinding.btnMain.isEnabled = true //버튼 활성화
        }
    }

    override fun onRestart() {
        super.onRestart()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("작성 중이던 내용이 있습니다")
            .setMessage("다시 작성하시겠습니까?")
            .setPositiveButton("예"){ _, _ ->
            }
            .setNegativeButton("아니오"){_,_ ->
                savedMesaage = ""
                message = ""
                viewbinding.txtEdit.setText("")
                viewbinding.btnMain.isEnabled = false

            }
            .setCancelable(false) //뒤로가기 눌렀을 때 버튼을 닫을 수 없게 구현
        builder.create().show() //화면에 보여주기
    }
    
}