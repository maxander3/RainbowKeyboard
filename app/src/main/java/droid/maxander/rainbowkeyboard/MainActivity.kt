package droid.maxander.rainbowkeyboard

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Window
import android.widget.Toast
import droid.maxander.rainbowkeyboard.databinding.ActivityMainBinding
import droid.maxander.rainbowkeyboard.databinding.ActivitySelectDifficultyBinding
import droid.maxander.rainbowkeyboard.databinding.ActivitySelectLevelBinding
import kotlin.properties.Delegates

var backPressedTime:Long=0
class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var difficultyBinding: ActivitySelectDifficultyBinding
    lateinit var easyBinding: ActivitySelectLevelBinding
    var levelComplete by Delegates.notNull<Int>()
    lateinit var btnSound:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        difficultyBinding= ActivitySelectDifficultyBinding.inflate(layoutInflater)
        easyBinding= ActivitySelectLevelBinding.inflate(layoutInflater)
        btnSound= MediaPlayer.create(this,R.raw.menu_sound)
        val sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this)
        levelComplete= sharedPreferences.getInt("levels_complete",0)
        when{
            levelComplete<=6->{
                difficultyBinding.easyLevelsComplete.text=levelComplete.toString()
                difficultyBinding.mediumLevelsComplete.text="0"
                difficultyBinding.hardLevelsComplete.text="0"
            }
            levelComplete in 7..12->{
                difficultyBinding.easyLevelsComplete.text="6"
                difficultyBinding.mediumLevelsComplete.text=(levelComplete-5).toString()
                difficultyBinding.hardLevelsComplete.text="0"
            }
            levelComplete in 13..18->{
                difficultyBinding.easyLevelsComplete.text="6"
                difficultyBinding.mediumLevelsComplete.text="6"
                difficultyBinding.hardLevelsComplete.text=(levelComplete-12).toString()
            }
        }


        soundBtn()
        //clickListener


        when (intent.getStringExtra("back")){
            "easy"->easyInit(levelComplete)
            "medium"->mediumInit(levelComplete)
            "hard"->hardInit(levelComplete)
            "difficulty"->setContentView(difficultyBinding.root)
            else->setContentView(mainBinding.root)
        }

        mainBinding.btnPlay.setOnClickListener{
            btnSound.start()
            setContentView(difficultyBinding.root)
        }

        //Кнопки__________________________________________________________
        //Выбор уровня сложности
        //Выбор Легкого уровня
        difficultyBinding.easyline.setOnClickListener{
            btnSound.start()
           easyInit(levelComplete)
        }

        //Выбор Легкого уровня
        //ВЫбор среднего уровня
        difficultyBinding.mediumline.setOnClickListener{
            btnSound.start()
            mediumInit(levelComplete)
        }
        //ВЫбор среднего уровня

        //Выбор сложного уровня
        difficultyBinding.hardline.setOnClickListener{
            btnSound.start()
            hardInit(levelComplete)
        }
        //Выбор сложного уровня
        difficultyBinding.shareBtn.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                R.string.share_txt
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        easyBinding.selectEasyLvlBack.setOnClickListener(){
            btnSound.start()
            setContentView(difficultyBinding.root)
        }
        difficultyBinding.soundBtn.setOnClickListener{
            soundBtn()
        }
    }
    private fun soundBtn(){
        val sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.getBoolean("sound",true)){
            editor.putBoolean("sound",false)
            editor.commit()
            difficultyBinding.soundBtn.setBackgroundResource(R.drawable.mute_sound_btn)
            btnSound.setVolume(0.0F, 0.0F)
        }else{
            editor.putBoolean("sound",true)
            editor.commit()
            difficultyBinding.soundBtn.setBackgroundResource(R.drawable.sound_btn)
            btnSound.setVolume(0.1F, 0.1F)
        }
        difficultyBinding.infoBtn.setOnClickListener{
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.about_developers_layout)
            dialog.show()
        }
    }
    private fun easyInit(levelsComplete: Int) {
        setContentView(easyBinding.root)
        easyBinding.difficultyTxt.setText(R.string.easy)
        easyBinding.easyLvl1.isEnabled=false
        easyBinding.easyLvl2.isEnabled=false
        easyBinding.easyLvl3.isEnabled=false
        easyBinding.easyLvl4.isEnabled=false
        easyBinding.easyLvl5.isEnabled=false
        easyBinding.easyLvl6.isEnabled=false
        easyBinding.easyLvl1.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl2.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl3.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl4.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl5.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl6.setBackgroundResource(R.mipmap.not_passed_btn)
            easyBinding.easyLvl1.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl1.isEnabled=true
        if (levelsComplete >= 1) {
            easyBinding.easyLvl2.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl2.isEnabled=true
        }
        if (levelsComplete >= 2) {
            easyBinding.easyLvl3.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl3.isEnabled=true
        }
        if (levelsComplete >= 3) {
            easyBinding.easyLvl4.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl4.isEnabled=true
        }
        if (levelsComplete >= 4) {
            easyBinding.easyLvl5.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl5.isEnabled=true
        }
        if (levelsComplete >= 5) {
            easyBinding.easyLvl6.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl6.isEnabled=true
        }
        fun easyListener(levelNumber: Int) {
            btnSound.start()
            val intent = Intent(this, EasyLvlActivity::class.java)
            intent.putExtra("level", levelNumber)
            startActivity(intent)
            finish()
        }
        easyBinding.easyLvl1.setOnClickListener {
            easyListener(1)
        }
        easyBinding.easyLvl2.setOnClickListener {
            easyListener(2)
        }
        easyBinding.easyLvl3.setOnClickListener {
            easyListener(3)
        }
        easyBinding.easyLvl4.setOnClickListener {
            easyListener(4)
        }
        easyBinding.easyLvl5.setOnClickListener {
            easyListener(5)
        }
        easyBinding.easyLvl6.setOnClickListener {
            easyListener(6)
        }
    }
    private fun mediumInit(levelsComplete: Int){
        setContentView(easyBinding.root)
        easyBinding.difficultyTxt.setText(R.string.medium)
        easyBinding.easyLvl1.isEnabled=false
        easyBinding.easyLvl2.isEnabled=false
        easyBinding.easyLvl3.isEnabled=false
        easyBinding.easyLvl4.isEnabled=false
        easyBinding.easyLvl5.isEnabled=false
        easyBinding.easyLvl6.isEnabled=false
        easyBinding.easyLvl1.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl2.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl3.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl4.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl5.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl6.setBackgroundResource(R.mipmap.not_passed_btn)
        if (levelsComplete >= 6) {
            easyBinding.easyLvl1.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl1.isEnabled=true
        }
        if (levelsComplete >= 7) {
            easyBinding.easyLvl2.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl2.isEnabled=true
        }
        if (levelsComplete >= 8) {
            easyBinding.easyLvl3.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl3.isEnabled=true
        }
        if (levelsComplete >= 9) {
            easyBinding.easyLvl4.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl4.isEnabled=true
        }
        if (levelsComplete >= 10) {
            easyBinding.easyLvl5.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl5.isEnabled=true
        }
        if (levelsComplete >= 11) {
            easyBinding.easyLvl6.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl6.isEnabled=true
        }
        fun mediumListener(levelNumber: Int) {
            btnSound.start()
            val intent = Intent(this, MediumLvlActivity::class.java)
            intent.putExtra("level", levelNumber)
            startActivity(intent)
            finish()
        }
        easyBinding.easyLvl1.setOnClickListener {
            mediumListener(7)
        }
        easyBinding.easyLvl2.setOnClickListener {
            mediumListener(8)
        }
        easyBinding.easyLvl3.setOnClickListener {
            mediumListener(9)
        }
        easyBinding.easyLvl4.setOnClickListener {
            mediumListener(10)
        }
        easyBinding.easyLvl5.setOnClickListener {
            mediumListener(11)
        }
        easyBinding.easyLvl6.setOnClickListener {
            mediumListener(12)
        }
    }
    private fun hardInit(levelsComplete: Int){
        setContentView(easyBinding.root)
        easyBinding.difficultyTxt.setText(R.string.hard)
        easyBinding.easyLvl1.isEnabled=false
        easyBinding.easyLvl2.isEnabled=false
        easyBinding.easyLvl3.isEnabled=false
        easyBinding.easyLvl4.isEnabled=false
        easyBinding.easyLvl5.isEnabled=false
        easyBinding.easyLvl6.isEnabled=false
        easyBinding.easyLvl1.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl2.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl3.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl4.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl5.setBackgroundResource(R.mipmap.not_passed_btn)
        easyBinding.easyLvl6.setBackgroundResource(R.mipmap.not_passed_btn)
        if (levelsComplete >= 12) {
            easyBinding.easyLvl1.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl1.isEnabled=true
        }
        if (levelsComplete >= 13) {
            easyBinding.easyLvl2.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl2.isEnabled=true
        }
        if (levelsComplete >= 14) {
            easyBinding.easyLvl3.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl3.isEnabled=true
        }
        if (levelsComplete >= 15) {
            easyBinding.easyLvl4.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl4.isEnabled=true
        }
        if (levelsComplete >= 16) {
            easyBinding.easyLvl5.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl5.isEnabled=true
        }
        if (levelsComplete >= 17) {
            easyBinding.easyLvl6.setBackgroundResource(R.drawable.select_lvl_btn)
            easyBinding.easyLvl6.isEnabled=true
        }
        fun hardListener(levelNumber: Int) {
            btnSound.start()
            val intent = Intent(this, HardLvlActivity::class.java)
            intent.putExtra("level", levelNumber)
            startActivity(intent)
            finish()
        }
        easyBinding.easyLvl1.setOnClickListener {
            hardListener(13)
        }
        easyBinding.easyLvl2.setOnClickListener {
            hardListener(14)
        }
        easyBinding.easyLvl3.setOnClickListener {
            hardListener(15)
        }
        easyBinding.easyLvl4.setOnClickListener {
            hardListener(16)
        }
        easyBinding.easyLvl5.setOnClickListener {
            hardListener(17)
        }
        easyBinding.easyLvl6.setOnClickListener {
            hardListener(18)
        }
    }
    override fun onBackPressed() {

        if (backPressedTime+2000>System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            backPressedTime=System.currentTimeMillis()
            Toast.makeText(baseContext,R.string.click_again_to_exit,Toast.LENGTH_SHORT).show()
        }
    }
}