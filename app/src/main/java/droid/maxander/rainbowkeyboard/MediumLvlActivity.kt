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
import droid.maxander.rainbowkeyboard.databinding.ActivityMediumLvlBinding
import kotlin.properties.Delegates

class MediumLvlActivity : AppCompatActivity() {
    private val viewModel: MediumViewModel by lazy {
        ViewModelProvider(this).get(MediumViewModel::class.java)
    }
    lateinit var binding: ActivityMediumLvlBinding
    private var level by Delegates.notNull<Int>()
    private val btnSound: MediaPlayer by lazy {
        MediaPlayer.create(this, R.raw.menu_sound)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediumLvlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        level = intent.getIntExtra("level", 1)
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
        fun cubeListener(vararg cubes: Int) {
            cubeSound.start()
            for (cube in cubes) {
                viewModel.changeTags(cube)
            }
            changeButtonBackground()
            if (!checkWin()) {
                checkLose()
            }
        }
        binding.button1.setOnClickListener {
            cubeListener(1, 2, 5)
        }
        binding.button2.setOnClickListener {
            cubeListener(1, 2, 3, 6)
        }
        binding.button3.setOnClickListener {
            cubeListener(2, 3, 4, 7)
        }
        binding.button4.setOnClickListener {
            cubeListener(3, 4, 8)
        }
        binding.button5.setOnClickListener {
            cubeListener(1, 5, 6, 9)
        }
        binding.button6.setOnClickListener {
            cubeListener(2, 5, 6, 7, 10)
        }
        binding.button7.setOnClickListener {
            cubeListener(3, 6, 7, 8, 11)
        }
        binding.button8.setOnClickListener {
            cubeListener(4, 7, 8, 12)
        }
        binding.button9.setOnClickListener {
            cubeListener(5, 9, 10, 13)
        }
        binding.button10.setOnClickListener {
            cubeListener(6, 9, 10, 11, 14)
        }
        binding.button11.setOnClickListener {
            cubeListener(7, 10, 11, 12, 15)
        }
        binding.button12.setOnClickListener {
            cubeListener(8, 11, 12, 16)
        }
        binding.button13.setOnClickListener {
            cubeListener(9, 13, 14)
        }
        binding.button14.setOnClickListener {
            cubeListener(10, 13, 14, 15)
        }
        binding.button15.setOnClickListener {
            cubeListener(11, 14, 15, 16)
        }
        binding.button16.setOnClickListener {
            cubeListener(12, 15, 16)
        }


        binding.lvlBack.setOnClickListener {
            btnSound.start()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("back", "medium")
            startActivity(intent)
            finish()
        }
        binding.restartBtn.setOnClickListener {
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
                dialog.dismiss()
            }
            dialog.show()


        }
        binding.menuBtn.setOnClickListener {
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
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("back", "medium")
        startActivity(intent)
        finish()
    }

    private fun changeButtonBackground() {
        viewModel.turnsLeft--
        binding.button1.tag = viewModel.tag1Button
        binding.button1.setBackgroundResource(binding.button1.tag as Int)
        binding.button2.tag = viewModel.tag2Button
        binding.button2.setBackgroundResource(binding.button2.tag as Int)
        binding.button3.tag = viewModel.tag3Button
        binding.button3.setBackgroundResource(binding.button3.tag as Int)
        binding.button4.tag = viewModel.tag4Button
        binding.button4.setBackgroundResource(binding.button4.tag as Int)
        binding.button5.tag = viewModel.tag5Button
        binding.button5.setBackgroundResource(binding.button5.tag as Int)
        binding.button6.tag = viewModel.tag6Button
        binding.button6.setBackgroundResource(binding.button6.tag as Int)
        binding.button7.tag = viewModel.tag7Button
        binding.button7.setBackgroundResource(binding.button7.tag as Int)
        binding.button8.tag = viewModel.tag8Button
        binding.button8.setBackgroundResource(binding.button8.tag as Int)
        binding.button9.tag = viewModel.tag9Button
        binding.button9.setBackgroundResource(binding.button9.tag as Int)
        binding.button10.tag = viewModel.tag10Button
        binding.button10.setBackgroundResource(binding.button10.tag as Int)
        binding.button11.tag = viewModel.tag11Button
        binding.button11.setBackgroundResource(binding.button11.tag as Int)
        binding.button12.tag = viewModel.tag12Button
        binding.button12.setBackgroundResource(binding.button12.tag as Int)
        binding.button13.tag = viewModel.tag13Button
        binding.button13.setBackgroundResource(binding.button13.tag as Int)
        binding.button14.tag = viewModel.tag14Button
        binding.button14.setBackgroundResource(binding.button14.tag as Int)
        binding.button15.tag = viewModel.tag15Button
        binding.button15.setBackgroundResource(binding.button15.tag as Int)
        binding.button16.tag = viewModel.tag16Button
        binding.button16.setBackgroundResource(binding.button16.tag as Int)
        binding.turnsLeft.text = viewModel.turnsLeft.toString()


    }

    private fun init() {
        binding.levelNumber.text = (level % 6).toString()
        binding.turnsTotal.text = viewModel.turnsTotal.toString()
        binding.turnsLeft.text = viewModel.turnsLeft.toString()
        binding.button1.tag = viewModel.tag1Button
        binding.button1.setBackgroundResource(binding.button1.tag as Int)
        binding.button2.tag = viewModel.tag2Button
        binding.button2.setBackgroundResource(binding.button2.tag as Int)
        binding.button3.tag = viewModel.tag3Button
        binding.button3.setBackgroundResource(binding.button3.tag as Int)
        binding.button4.tag = viewModel.tag4Button
        binding.button4.setBackgroundResource(binding.button4.tag as Int)
        binding.button5.tag = viewModel.tag5Button
        binding.button5.setBackgroundResource(binding.button5.tag as Int)
        binding.button6.tag = viewModel.tag6Button
        binding.button6.setBackgroundResource(binding.button6.tag as Int)
        binding.button7.tag = viewModel.tag7Button
        binding.button7.setBackgroundResource(binding.button7.tag as Int)
        binding.button8.tag = viewModel.tag8Button
        binding.button8.setBackgroundResource(binding.button8.tag as Int)
        binding.button9.tag = viewModel.tag9Button
        binding.button9.setBackgroundResource(binding.button9.tag as Int)
        binding.button10.tag = viewModel.tag10Button
        binding.button10.setBackgroundResource(binding.button10.tag as Int)
        binding.button11.tag = viewModel.tag11Button
        binding.button11.setBackgroundResource(binding.button11.tag as Int)
        binding.button12.tag = viewModel.tag12Button
        binding.button12.setBackgroundResource(binding.button12.tag as Int)
        binding.button13.tag = viewModel.tag13Button
        binding.button13.setBackgroundResource(binding.button13.tag as Int)
        binding.button14.tag = viewModel.tag14Button
        binding.button14.setBackgroundResource(binding.button14.tag as Int)
        binding.button15.tag = viewModel.tag15Button
        binding.button15.setBackgroundResource(binding.button15.tag as Int)
        binding.button16.tag = viewModel.tag16Button
        binding.button16.setBackgroundResource(binding.button16.tag as Int)
        questionDialog()
    }

    private fun checkWin(): Boolean {
        if (binding.button1.tag == viewModel.winList[0] &&
            binding.button2.tag == viewModel.winList[1] &&
            binding.button3.tag == viewModel.winList[2] &&
            binding.button4.tag == viewModel.winList[3] &&
            binding.button5.tag == viewModel.winList[4] &&
            binding.button6.tag == viewModel.winList[5] &&
            binding.button7.tag == viewModel.winList[6] &&
            binding.button8.tag == viewModel.winList[7] &&
            binding.button9.tag == viewModel.winList[8] &&
            binding.button10.tag == viewModel.winList[9] &&
            binding.button11.tag == viewModel.winList[10] &&
            binding.button12.tag == viewModel.winList[11] &&
            binding.button13.tag == viewModel.winList[12] &&
            binding.button14.tag == viewModel.winList[13] &&
            binding.button15.tag == viewModel.winList[14] &&
            binding.button16.tag == viewModel.winList[15]
        ) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = sharedPreferences.edit()
            editor.putInt("levels_complete", level)
            editor.commit()

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
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
            nextBtn.setOnClickListener {
                val intent = Intent(this, HardLvlActivity::class.java)
                intent.putExtra("level", 13)
                startActivity(intent)
                finish()
            }
        }
            dialog.show()

            return true
        } else return false
    }

    private fun checkLose() {
        if (viewModel.turnsLeft <= 0) {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.lose_layout)
            val homeBtn = dialog.findViewById(R.id.lose_btn_home) as Button
            val restartBtn = dialog.findViewById(R.id.lose_btn_restart) as Button
            homeBtn.setOnClickListener {
                btnSound.start()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("back", "easy")
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

    private fun questionDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.medium_question_layout)
        val btnList = listOf<Button>(dialog.findViewById(R.id.question_button1),
            dialog.findViewById(R.id.question_button2),
            dialog.findViewById(R.id.question_button3),
            dialog.findViewById(R.id.question_button4),
            dialog.findViewById(R.id.question_button5),
            dialog.findViewById(R.id.question_button6),
            dialog.findViewById(R.id.question_button7),
            dialog.findViewById(R.id.question_button8),
            dialog.findViewById(R.id.question_button9),
            dialog.findViewById(R.id.question_button10),
            dialog.findViewById(R.id.question_button11),
            dialog.findViewById(R.id.question_button12),
            dialog.findViewById(R.id.question_button13),
            dialog.findViewById(R.id.question_button14),
            dialog.findViewById(R.id.question_button15),
            dialog.findViewById(R.id.question_button16))
        for (i in 0..15) {
            btnList[i].setBackgroundResource(viewModel.winList[i])
        }
        dialog.show()
    }
}