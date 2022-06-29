package droid.maxander.rainbowkeyboard

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Window
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import droid.maxander.rainbowkeyboard.databinding.ActivityEasyLvlBinding
import kotlin.properties.Delegates

class HardLvlActivity : AppCompatActivity() {
    private val viewModel:HardViewModel by lazy {
        ViewModelProvider(this).get(HardViewModel::class.java)
    }
    lateinit var binding: ActivityEasyLvlBinding
    private var level by Delegates.notNull<Int>()
    private val btnSound: MediaPlayer by lazy{
        MediaPlayer.create(this,R.raw.menu_sound)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEasyLvlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        level=intent.getIntExtra("level",1)
        //sound
        val cubeSound: MediaPlayer = MediaPlayer.create(this,R.raw.game_sound)
        val sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this)
        if (sharedPreferences.getBoolean("sound",false)){
            cubeSound.setVolume(0.0f,0.0f)
            btnSound.setVolume(0.0f,0.0f)
        }else{
            cubeSound.setVolume(0.1f,0.1f)
            btnSound.setVolume(0.1f,0.1f)
        }



        //sound
        //INIT
        viewModel.setFirstRes(level)
        init()
        //Buttons
        fun cubeListener(vararg cubes:Int){
            cubeSound.start()
            for (cube in cubes){
                viewModel.changeTags(cube)
            }
            changeButtonBackground()
            if (!checkWin()) {
                checkLose()
            }
        }
        binding.easyButton1.setOnClickListener{
            cubeListener(1,2,4)
        }
        binding.easyButton2.setOnClickListener{
            cubeListener(1,2,3,5)
        }
        binding.easyButton3.setOnClickListener{
            cubeListener(2,3,6)
        }
        binding.easyButton4.setOnClickListener{
            cubeListener(1,4,5,7)
        }
        binding.easyButton5.setOnClickListener{
            cubeListener(2,4,5,6,8)
        }
        binding.easyButton6.setOnClickListener{
            cubeListener(3,5,6,9)
        }
        binding.easyButton7.setOnClickListener{
            cubeListener(4,7,8)
        }
        binding.easyButton8.setOnClickListener{
            cubeListener(5,7,8,9)
        }
        binding.easyButton9.setOnClickListener{
            cubeListener(6,8,9)
        }
        binding.easyLvlBack.setOnClickListener{
            btnSound.start()
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("back","hard")
            startActivity(intent)
            finish()
        }
        binding.easyRestartBtn.setOnClickListener{
            btnSound.start()
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.restart_layout)
            val yesBtn = dialog.findViewById(R.id.restart_yesBtn) as Button
            val noBtn = dialog.findViewById(R.id.restart_noBtn) as Button
            yesBtn.setOnClickListener {
                btnSound.start()
                viewModel.setFirstRes(level)
                init()
                dialog.dismiss()
            }
            noBtn.setOnClickListener {
                btnSound.start()
                dialog.dismiss() }
            dialog.show()


        }
        binding.easyMenuBtn.setOnClickListener{
            btnSound.start()
            questionDialog()
        }
    }

    override fun onStart() {
        super.onStart()
        startService(Intent(this, MyService::class.java))
    }
    override fun onPause() {
        super.onPause()
        stopService(Intent(this, MyService::class.java))
    }

    override fun onBackPressed() {
        val intent= Intent(this,MainActivity::class.java)
        intent.putExtra("back","hard")
        startActivity(intent)
        finish()
    }

    private fun init(){
        binding.easyLevelNumber.text=(level%6).toString()
        binding.easyTurnsTotal.text = viewModel.turnsTotal.toString()
        binding.easyTurnsLeft.text = viewModel.turnsLeft.toString()
        binding.easyButton1.tag=viewModel.tag1Button
        binding.easyButton1.setBackgroundResource(binding.easyButton1.tag as Int)
        binding.easyButton2.tag=viewModel.tag2Button
        binding.easyButton2.setBackgroundResource(binding.easyButton2.tag as Int)
        binding.easyButton3.tag=viewModel.tag3Button
        binding.easyButton3.setBackgroundResource(binding.easyButton3.tag as Int)
        binding.easyButton4.tag=viewModel.tag4Button
        binding.easyButton4.setBackgroundResource(binding.easyButton4.tag as Int)
        binding.easyButton5.tag=viewModel.tag5Button
        binding.easyButton5.setBackgroundResource(binding.easyButton5.tag as Int)
        binding.easyButton6.tag=viewModel.tag6Button
        binding.easyButton6.setBackgroundResource(binding.easyButton6.tag as Int)
        binding.easyButton7.tag=viewModel.tag7Button
        binding.easyButton7.setBackgroundResource(binding.easyButton7.tag as Int)
        binding.easyButton8.tag=viewModel.tag8Button
        binding.easyButton8.setBackgroundResource(binding.easyButton8.tag as Int)
        binding.easyButton9.tag=viewModel.tag9Button
        binding.easyButton9.setBackgroundResource(binding.easyButton9.tag as Int)
        questionDialog()
    }

    //функция берем из виюв модели тэги и меняет тэг кнопки и ее фон
    private fun changeButtonBackground(){
        viewModel.turnsLeft--
        binding.easyButton1.tag=viewModel.tag1Button
        binding.easyButton1.setBackgroundResource(binding.easyButton1.tag as Int)
        binding.easyButton2.tag=viewModel.tag2Button
        binding.easyButton2.setBackgroundResource(binding.easyButton2.tag as Int)
        binding.easyButton3.tag=viewModel.tag3Button
        binding.easyButton3.setBackgroundResource(binding.easyButton3.tag as Int)
        binding.easyButton4.tag=viewModel.tag4Button
        binding.easyButton4.setBackgroundResource(binding.easyButton4.tag as Int)
        binding.easyButton5.tag=viewModel.tag5Button
        binding.easyButton5.setBackgroundResource(binding.easyButton5.tag as Int)
        binding.easyButton6.tag=viewModel.tag6Button
        binding.easyButton6.setBackgroundResource(binding.easyButton6.tag as Int)
        binding.easyButton7.tag=viewModel.tag7Button
        binding.easyButton7.setBackgroundResource(binding.easyButton7.tag as Int)
        binding.easyButton8.tag=viewModel.tag8Button
        binding.easyButton8.setBackgroundResource(binding.easyButton8.tag as Int)
        binding.easyButton9.tag=viewModel.tag9Button
        binding.easyButton9.setBackgroundResource(binding.easyButton9.tag as Int)
        binding.easyTurnsLeft.text = viewModel.turnsLeft.toString()


    }
    private fun checkLose(){
        if (viewModel.turnsLeft<=0){
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.lose_layout)
            val homeBtn = dialog.findViewById(R.id.lose_btn_home) as Button
            val restartBtn = dialog.findViewById(R.id.lose_btn_restart) as Button
            homeBtn.setOnClickListener {
                btnSound.start()
                val intent= Intent(this,MainActivity::class.java)
                intent.putExtra("back","easy")
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            restartBtn.setOnClickListener {
                btnSound.start()
                viewModel.setFirstRes(level)
                init()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    private fun checkWin(): Boolean {
        if (binding.easyButton1.tag == viewModel.winList[0] &&
            binding.easyButton2.tag == viewModel.winList[1] &&
            binding.easyButton3.tag == viewModel.winList[2] &&
            binding.easyButton4.tag == viewModel.winList[3] &&
            binding.easyButton5.tag == viewModel.winList[4] &&
            binding.easyButton6.tag == viewModel.winList[5] &&
            binding.easyButton7.tag == viewModel.winList[6] &&
            binding.easyButton8.tag == viewModel.winList[7] &&
            binding.easyButton9.tag == viewModel.winList[8]
        ) {


            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            if (sharedPreferences.getInt("levels_complete",0)<level) {
                val editor = sharedPreferences.edit()
                editor.putInt("levels_complete", level)
                editor.commit()
            }
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.win_layout)
            val homeBtn = dialog.findViewById(R.id.win_btn_home) as Button
            val nextBtn = dialog.findViewById(R.id.win_btn_next) as Button
            homeBtn.setOnClickListener {
                btnSound.start()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("back", "difficulty")
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            if (level % 6 != 0) {
                nextBtn.setOnClickListener {
                    btnSound.start()
                    level++
                    viewModel.setFirstRes(level)
                    init()
                    dialog.dismiss()
                }
            } else {
                nextBtn.setBackgroundResource(R.drawable.info_btn)
                nextBtn.text=""
                nextBtn.setOnClickListener {
                    //TODO кнопка о разработчиках на последнем левеле
                }
            }
            dialog.show()
            return true
        } else return false
    }
    private fun questionDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.question_activity)
        val btnList = listOf<Button>(dialog.findViewById(R.id.question_button1),
            dialog.findViewById(R.id.question_button2),
            dialog.findViewById(R.id.question_button3),
            dialog.findViewById(R.id.question_button4),
            dialog.findViewById(R.id.question_button5),
            dialog.findViewById(R.id.question_button6),
            dialog.findViewById(R.id.question_button7),
            dialog.findViewById(R.id.question_button8),
            dialog.findViewById(R.id.question_button9))
        for (i in 0..8) {
            btnList[i].setBackgroundResource(viewModel.winList[i])
        }
        dialog.show()
    }
}
