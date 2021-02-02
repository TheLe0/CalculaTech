package com.leotosin.calculatech.model

class Calculator(override var lastOperating: Double = 0.00, override var lastOperator: String = "") : Memento
{
    var operating :Double = 0.00
    private var memory :Double = 0.00

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
                this.lastOperator = op
                this.lastOperating = this.operating
            }
        }
    }

}