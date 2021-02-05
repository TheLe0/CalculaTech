package com.leotosin.calculatech.model

import android.widget.Toast
import kotlin.math.sqrt

import com.leotosin.calculatech.util.MathUtil

class Calculator(override var lastOperating: Double = 0.00, override var lastOperator: String = "") : Memento
{
    var operating :Double = 0.00
    var memory :Double = 0.00
    private var caretaker :Caretaker? = null

    private fun performLastOperation()
    {
        if (this.lastOperator != "")
        {
            when (this.lastOperator)
            {
                Operator.SUM ->
                {
                    this.operating += this.lastOperating
                }
                Operator.SUBTRACT ->
                {
                    this.operating = this.lastOperating - this.operating
                }
                Operator.MULTIPLICATION ->
                {
                    this.operating *= this.lastOperating
                }
                Operator.DIVISION ->
                {
                    if (this.operating != 0.00)
                    {
                        this.operating = this.lastOperating / this.operating
                    }
                }
            }
        }
    }

    fun performOperation(op :String)
    {
        this.caretaker = Caretaker(this.lastOperating, op, MementoState.NONE)

        when (op)
        {
            Operator.PERCENT ->
            {
                this.operating = (this.lastOperating * this.operating / 100)
            }
            Operator.CHANGE_SIGNAL ->
            {
                this.operating = -1*this.operating
            }
            Operator.EXPONENTIAL ->
            {
                this.operating *= this.operating
            }
            Operator.SQUARE ->
            {
                this.operating = sqrt(this.operating)
            }
            Operator.FACTORIAL ->
            {
                this.operating = MathUtil.factorial(this.operating.toInt()).toDouble()

                if (this.operating >= Double.MAX_VALUE)
                {
                    this.operating = 0.00
                }
            }
            Operator.CLEAR ->
            {
                this.operating = 0.00
                this.lastOperating = 0.00
                this.memory = 0.00
                this.lastOperator = op
            }
            else ->
            {
                this.performLastOperation()
                this.lastOperator = op
                this.lastOperating = this.operating
            }
        }
    }

    fun performMemoryOperation(opm :String)
    {
        when (opm)
        {
            Operator.MEMORY_CLEAR ->
            {
                this.memory = 0.00
            }
            Operator.MEMORY_SUM ->
            {
                this.memory += this.operating
            }
            Operator.MEMORY_SUBTRACT ->
            {
                this.memory -= this.operating
            }
            Operator.MEMORY_RECOVERY ->
            {
                this.operating = this.memory
            }
        }
    }

    override fun undo()
    {
        if (this.caretaker != null && this.caretaker!!.state != MementoState.UNDO)
        {
            this.caretaker = Caretaker(
                this.operating,
                this.lastOperator,
                MementoState.UNDO
            )
            this.lastOperator = ""
            this.lastOperating = 0.00
            this.operating = this.caretaker!!.operating
        }
    }

    override fun redo()
    {
        if (this.caretaker != null && this.caretaker!!.state == MementoState.REDO)
        {
            this.lastOperator = this.caretaker!!.operator
            this.lastOperating = this.caretaker!!.operating

            this.caretaker = Caretaker(
                    this.operating,
                    this.lastOperator,
                    MementoState.REDO
            )
        }
    }
}