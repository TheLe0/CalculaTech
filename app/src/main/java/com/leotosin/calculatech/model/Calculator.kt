package com.leotosin.calculatech.model

class Calculator(override var lastOperating: Double = 0.00, override var lastOperator: String = "") : Memento
{
    var operating :Double = 0.00
    private var memory :Double = 0.00

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
                    this.operating -= this.lastOperating
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
}