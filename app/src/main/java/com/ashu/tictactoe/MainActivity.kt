@file:Suppress("UNREACHABLE_CODE")

package com.ashu.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity(),View.OnClickListener {

    var  Player = true
    var Trun_count = 0
    var boardStatus = Array(3){IntArray(3)}

    lateinit var board : Array<Array<Button>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9),
        )
        for (i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        resetbutton.setOnClickListener(){
            Trun_count = 0
            Player = true
            updateDisplay("Player 1 turn")
            initializeBoardStatus()

        }
    }

    private fun initializeBoardStatus()
    {
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                boardStatus[i][j] = -1

            }
        }
        for (i in board)
        {
            for (button in i)
            {
                button.isEnabled = true
                button.text=""
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id) {
                R.id.button1 -> {
                    updateValue(row = 0, col = 0, player = Player)

                }
                R.id.button2 -> {
                    updateValue(row = 0, col = 1, player = Player)
                }

                R.id.button3 -> {
                    updateValue(row = 0, col = 2, player = Player)
                }
                R.id.button4 -> {
                    updateValue(row = 1, col = 0, player = Player)
                }
                R.id.button5 -> {
                    updateValue(row = 1, col = 1, player = Player)
                }
                R.id.button6 -> {
                    updateValue(row = 1, col = 2, player = Player)
                }
                R.id.button7 -> {
                    updateValue(row = 2, col = 0, player = Player)
                }
                R.id.button8 -> {
                    updateValue(row = 2, col = 1, player = Player)
                }
                R.id.button9 -> {
                    updateValue(row = 2, col = 2, player = Player)
                }
            }
        }
        Trun_count++
        Player = !Player
        if(Player){
            updateDisplay("PLAYER X TURN")
        }else{
            updateDisplay("PLAYER O TURN")
        }

        if(Trun_count == 9)
        {
            updateDisplay("GAME DRAW")
        }
        checkWinner()
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text= if(Player){"X"}
        else{"O"}
        val value = if (Player)1 else 0
        val apply = board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col]= value
    }


    private fun checkWinner() {
        for (i in 0..2){
        if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus [i][2])
            if(boardStatus[i][0]==1){
                updateDisplay("Player 1 is Winner")
            break}
            else if (boardStatus[i][0]==0){
                updateDisplay("Player 2 is Winner")
                break
            }
    }
        for (i in 0..2)
        {
            if (boardStatus[0][i]== boardStatus [1][i] && boardStatus[0][i] == boardStatus [2][i])
            if(boardStatus[0][i]==1)
            {
                updateDisplay("Player 1 is Winner")
                break
            }

            else if (boardStatus[0][i]==1){

            updateDisplay("Player 2 is Winner")

        }


        }

        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player 1 is Winner")

            } else if (boardStatus[0][0] == 0){
                updateDisplay("Player 2 is Winner")
            }
        }

        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player 1 is Winner")
            } else if
                (boardStatus[0][2] == 0){
                updateDisplay("Player 2 is Winner")
            }
        }
    }

    private fun updateDisplay(text: String) {
        displaynTv.text = text
        if (text.contains("Winner")){
            disablebutton()
        }

    }
    private fun disablebutton()
    {
        for (i in board){
            for (button in i){
                button.isEnabled = false
            }

    }

}
}