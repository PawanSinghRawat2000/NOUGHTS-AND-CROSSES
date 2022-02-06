package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.game_activity.*


class gameActivity : AppCompatActivity() {

    enum class Turn{
        NOUGHT,
        CROSS
    }
    private var firstTurn= Turn.NOUGHT
    private var currTurn =Turn.CROSS

    private var crossesScore = 0
    private var noughtsScore = 0

    private var boardList = mutableListOf<Button>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.game_activity)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(a1)
        boardList.add(a2)
        boardList.add(a3)
        boardList.add(b1)
        boardList.add(b2)
        boardList.add(b3)
        boardList.add(c1)
        boardList.add(c2)
        boardList.add(c3)

    }

    fun boardTapped(view: View)
    {
        if(view !is Button)
            return
        addToBoard(view)

        if(checkForVictory(NOUGHT))
        {
            noughtsScore++
            result("Noughts Win!")
        }
        else if(checkForVictory(CROSS))
        {
            crossesScore++
            result("Crosses Win!")
        }

        if(fullBoard())
        {
            result("Draw")
        }

    }

    private fun fullBoard(): Boolean {
        for(button in boardList)
        {
            if(button.text == "")
                return false
        }
        return true
    }

    private fun result(title: String) {
        val message = "\nNoughts $noughtsScore\n\nCrosses $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for(button in boardList)
        {
            button.text = ""
        }

        if(firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if(firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currTurn = firstTurn
        setTurnLabel()
    }

    private fun checkForVictory(s: String): Boolean {
        //horizontal victory
        if(match(a1,s) && match(a2,s) && match(a3,s))
            return true
        if(match(b1,s) && match(b2,s) && match(b3,s))
            return true
        if(match(c1,s) && match(c2,s) && match(c3,s))
            return true

        //Vertical Victory
        if(match(a1,s) && match(b1,s) && match(c1,s))
            return true
        if(match(a2,s) && match(b2,s) && match(c2,s))
            return true
        if(match(a3,s) && match(b3,s) && match(c3,s))
            return true

        //Diagonal Victory
        if(match(a1,s) && match(b2,s) && match(c3,s))
            return true
        if(match(a3,s) && match(b2,s) && match(c1,s))
            return true

        return false
    }
    private fun match(button: Button, symbol : String): Boolean = button.text == symbol


    private fun addToBoard(button: Button)
    {
        if(button.text != "")
            return

        if(currTurn == Turn.NOUGHT)
        {
            button.text = NOUGHT
            currTurn = Turn.CROSS
        }
        else if(currTurn == Turn.CROSS)
        {
            button.text = CROSS
            currTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if(currTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        else if(currTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        turnTV.text = turnText
    }

    companion object
    {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }


}