package droid.maxander.rainbowkeyboard

import androidx.lifecycle.ViewModel

class HardViewModel: ViewModel() {
    var tag1Button:Int = R.drawable.cube_1
    var tag2Button:Int = R.drawable.cube_1
    var tag3Button:Int = R.drawable.cube_1
    var tag4Button:Int = R.drawable.cube_1
    var tag5Button:Int = R.drawable.cube_1
    var tag6Button:Int = R.drawable.cube_1
    var tag7Button:Int = R.drawable.cube_1
    var tag8Button:Int = R.drawable.cube_1
    var tag9Button:Int = R.drawable.cube_1
    var turnsLeft:Int = 10
    var turnsTotal:Int = 10
    var levelNumber:Int = 1
    var winList=mutableListOf(0,1,2,3,4,5,6,7,8)


    fun changeTags(tag:Int){
        when (tag) {
            1 -> tag1Button = when (tag1Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            2 -> tag2Button = when (tag2Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            3 -> tag3Button = when (tag3Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            4 -> tag4Button = when (tag4Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            5 -> tag5Button = when (tag5Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            6 -> tag6Button = when (tag6Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            7 -> tag7Button = when (tag7Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            8 -> tag8Button = when (tag8Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
            9 -> tag9Button = when (tag9Button){
                R.drawable.cube_1->R.drawable.cube_2
                R.drawable.cube_2->R.drawable.cube_3
                else->R.drawable.cube_1
            }
        }
    }

    fun setFirstRes(level:Int){
        levelNumber= level
        val res=LevelResources()
        val list =when (levelNumber) {
            13-> res.level31ArrayButtons
            14-> res.level32ArrayButtons
            15-> res.level33ArrayButtons
            16-> res.level34ArrayButtons
            17-> res.level35ArrayButtons
            18-> res.level36ArrayButtons
            else -> {res.endlessArrayButtons}
        }
        tag1Button= R.drawable.cube_1
        tag2Button= R.drawable.cube_1
        tag3Button= R.drawable.cube_1
        tag4Button= R.drawable.cube_1
        tag5Button= R.drawable.cube_1
        tag6Button= R.drawable.cube_1
        tag7Button= R.drawable.cube_1
        tag8Button= R.drawable.cube_1
        tag9Button= R.drawable.cube_1
        for (i in 0..8){
            winList[i]=list[i]
        }
        turnsLeft=turnsTotal
    }

}