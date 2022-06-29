package droid.maxander.rainbowkeyboard

import androidx.lifecycle.ViewModel

class EasyViewModel : ViewModel() {
    var tag1Button: Int = R.drawable.cube_1
    var tag2Button: Int = R.drawable.cube_1
    var tag3Button: Int = R.drawable.cube_1
    var tag4Button: Int = R.drawable.cube_1
    var tag5Button: Int = R.drawable.cube_1
    var tag6Button: Int = R.drawable.cube_1
    var tag7Button: Int = R.drawable.cube_1
    var tag8Button: Int = R.drawable.cube_1
    var tag9Button: Int = R.drawable.cube_1
    var turnsLeft: Int = 10
    var turnsTotal: Int = 10
    var levelNumber: Int = 1
    var winList = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)


    fun changeTags(tag: Int) {
        when (tag) {
            1 -> tag1Button = if (tag1Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            2 -> tag2Button = if (tag2Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            3 -> tag3Button = if (tag3Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            4 -> tag4Button = if (tag4Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            5 -> tag5Button = if (tag5Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            6 -> tag6Button = if (tag6Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            7 -> tag7Button = if (tag7Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            8 -> tag8Button = if (tag8Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            9 -> tag9Button = if (tag9Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
        }
    }

    fun setFirstRes(level: Int) {
        levelNumber = level
        val res = LevelResources()
        val list = when (levelNumber) {
            1 -> res.level1ArrayButtons
            2 -> res.level2ArrayButtons
            3 -> res.level3ArrayButtons
            4 -> res.level4ArrayButtons
            5 -> res.level5ArrayButtons
            6 -> res.level6ArrayButtons
            else -> {
                res.endlessArrayButtons
            }
        }
        tag1Button = R.drawable.cube_1
        tag2Button = R.drawable.cube_1
        tag3Button = R.drawable.cube_1
        tag4Button = R.drawable.cube_1
        tag5Button = R.drawable.cube_1
        tag6Button = R.drawable.cube_1
        tag7Button = R.drawable.cube_1
        tag8Button = R.drawable.cube_1
        tag9Button = R.drawable.cube_1
        for (i in 0..8) {
            winList[i] = list[i]
        }
        turnsTotal = list[9]
        turnsLeft = turnsTotal

    }

}