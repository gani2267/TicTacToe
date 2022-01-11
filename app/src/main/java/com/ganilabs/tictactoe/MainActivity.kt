package com.ganilabs.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {

    var Player = true
    var Turn_Count = 0
    var BoardVal = Array(3){IntArray(3)}

    lateinit var Board : Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Board = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )

        for (i in Board){
            for(button in i){
                button.setOnClickListener (this)
            }
        }

        resetBtn.setOnClickListener {
            Turn_Count = 0
            Player = true
            initializeBoardStatus()
        }

        initializeBoardStatus()
    }

    private fun initializeBoardStatus() {

        for(i in 0..2){
            for(j in 0..2){
                BoardVal[i][j] = -1
                Board[i][j].isEnabled = true
                Board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View) {

        when(view.id){

            R.id.btn1 -> { updateValue(row=0, col =0,player = Player)}
            R.id.btn2 -> { updateValue(row=0, col =1,player = Player)}
            R.id.btn3 -> { updateValue(row=0, col =2,player = Player)}
            R.id.btn4 -> { updateValue(row=1, col =0,player = Player)}
            R.id.btn5 -> { updateValue(row=1, col =1,player = Player)}
            R.id.btn6 -> { updateValue(row=1, col =2,player = Player)}
            R.id.btn7 -> { updateValue(row=2, col =0,player = Player)}
            R.id.btn8 -> { updateValue(row=2, col =1,player = Player)}
            R.id.btn9 -> { updateValue(row=2, col =2,player = Player)}
        }

        Player = !Player
        Turn_Count++

        if(Player){
            updateDisplay("Player X Turn")
        }else{
            updateDisplay("Player 0 Turn")
        }

        if(Turn_Count == 9){
            updateDisplay("Match Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {

        // Horizontal Check
        for (i in 0..2){
                if(BoardVal[i][0]==BoardVal[i][1] && BoardVal[i][0]==BoardVal[i][2]){
                    if(BoardVal[i][0]==1){
                        updateDisplay("Player X Winner")
                        break
                    }else if(BoardVal[i][0]==0){
                        updateDisplay("Player 0 Winner")
                        break
                    }
                }
        }

        // Vertical Check
        for (i in 0..2){
            if(BoardVal[0][i]==BoardVal[1][i] && BoardVal[0][i]==BoardVal[2][i]){
                if(BoardVal[0][i]==1){
                    updateDisplay("Player X Winner")
                    break
                }else if(BoardVal[0][i]==0){
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
        }

        // Diagonal Check
        if(BoardVal[0][0]==BoardVal[1][1] && BoardVal[0][0]==BoardVal[2][2]){
            if(BoardVal[0][0]==1){
                updateDisplay("Player X Winner")
            }else if(BoardVal[0][0]==0){
                updateDisplay("Player 0 Winner")
            }
        }

        if(BoardVal[0][2]==BoardVal[1][1] && BoardVal[0][2]==BoardVal[2][0]){
            if(BoardVal[0][2]==1){
                updateDisplay("Player X Winner")
            }else if(BoardVal[0][2]==0){
                updateDisplay("Player 0 Winner")
            }
        }

    }

    private fun disableButton(){
        for (i in Board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateDisplay(s: String) {
        txt.text = s

        if(s.contains("Winner")){
            disableButton()
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        if(player) {
            Board[row][col].apply {
                text = "X"
                isEnabled = false
            }
            BoardVal[row][col] = 1
        }else{
            Board[row][col].apply {
                text = "0"
                isEnabled = false
            }
            BoardVal[row][col] = 0
        }
    }
}