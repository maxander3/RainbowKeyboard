package droid.maxander.rainbowkeyboard

import androidx.lifecycle.ViewModel

class MediumViewModel : ViewModel() {
    var tag1Button: Int = R.drawable.cube_1
    var tag2Button: Int = R.drawable.cube_1
    var tag3Button: Int = R.drawable.cube_1
    var tag4Button: Int = R.drawable.cube_1
    var tag5Button: Int = R.drawable.cube_1
    var tag6Button: Int = R.drawable.cube_1
    var tag7Button: Int = R.drawable.cube_1
    var tag8Button: Int = R.drawable.cube_1
    var tag9Button: Int = R.drawable.cube_1
    var tag10Button: Int = R.drawable.cube_1
    var tag11Button: Int = R.drawable.cube_1
    var tag12Button: Int = R.drawable.cube_1
    var tag13Button: Int = R.drawable.cube_1
    var tag14Button: Int = R.drawable.cube_1
    var tag15Button: Int = R.drawable.cube_1
    var tag16Button: Int = R.drawable.cube_1
    var turnsLeft: Int = 10
    var turnsTotal: Int = 10
    var levelNumber: Int = 1
    var winList = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

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
            10 -> tag10Button = if (tag10Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            11 -> tag11Button = if (tag11Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            12 -> tag12Button = if (tag12Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            13 -> tag13Button = if (tag13Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            14 -> tag14Button = if (tag14Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            15 -> tag15Button = if (tag15Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
            16 -> tag16Button = if (tag16Button == R.drawable.cube_1) R.drawable.cube_2
            else R.drawable.cube_1
        }
    }

    fun setFirstRes(level: Int) {
        levelNumber = level
        val res = LevelResources()
        val list = when (levelNumber) {
            7 -> res.level21ArrayButtons
            8 -> res.level22ArrayButtons
            9 -> res.level23ArrayButtons
            10 -> res.level24ArrayButtons
            11 -> res.level25ArrayButtons
            12 -> res.level26ArrayButtons
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
        tag10Button = R.drawable.cube_1
        tag11Button = R.drawable.cube_1
        tag12Button = R.drawable.cube_1
        tag13Button = R.drawable.cube_1
        tag14Button = R.drawable.cube_1
        tag15Button = R.drawable.cube_1
        tag16Button = R.drawable.cube_1
        turnsTotal = R.drawable.cube_1
        turnsTotal = list[16]
        turnsLeft = turnsTotal
        for (i in 0..15) {
            winList[i] = list[i]
        }
    }
}