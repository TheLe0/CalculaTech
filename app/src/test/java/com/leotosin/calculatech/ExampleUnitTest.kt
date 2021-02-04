package com.leotosin.calculatech

import org.junit.Test

import org.junit.Assert.*

import com.leotosin.calculatech.model.Calculator
import com.leotosin.calculatech.model.Operator

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val calculator :Calculator = Calculator()

    @Test
    fun addition_isCorrect()
    {
        calculator.operating = 2.00
        calculator.lastOperator = Operator.SUM
        calculator.lastOperating = 2.00

        calculator.performOperation(Operator.EQUAL)

        assertEquals(4.00, calculator.operating, 0.00)
    }

    @Test
    fun subtraction_isCorrect()
    {
        calculator.operating = 2.00
        calculator.lastOperator = Operator.SUBTRACT
        calculator.lastOperating = 4.00

        calculator.performOperation(Operator.EQUAL)

        assertEquals(2.00, calculator.operating, 0.00)
    }

    @Test
    fun multiplication_isCorrect()
    {
        calculator.operating = 2.00
        calculator.lastOperator = Operator.MULTIPLICATION
        calculator.lastOperating = 2.00

        calculator.performOperation(Operator.EQUAL)

        assertEquals(4.00, calculator.operating, 0.00)
    }

    @Test
    fun division_isCorrect()
    {
        calculator.operating = 2.00
        calculator.lastOperator = Operator.DIVISION
        calculator.lastOperating = 2.00

        calculator.performOperation(Operator.EQUAL)

        assertEquals(1.00, calculator.operating, 0.00)
    }

    @Test
    fun percent_isCorrect()
    {
        calculator.operating = 200.00
        calculator.lastOperator = Operator.PERCENT
        calculator.lastOperating = 100.00

        calculator.performOperation(Operator.PERCENT)

        assertEquals(200.00, calculator.operating, 0.00)
    }

    @Test
    fun changeSignal_isCorrect()
    {
        calculator.operating = 200.00

        calculator.performOperation(Operator.CHANGE_SIGNAL)

        assertEquals(-200.00, calculator.operating, 0.00)
    }

    @Test
    fun clear_isCorrect()
    {
        calculator.operating = 200.00

        calculator.performOperation(Operator.CLEAR)

        assertEquals(0.00, calculator.operating, 0.00)
    }

    @Test
    fun square_isCorrect()
    {
        calculator.operating = 121.00

        calculator.performOperation(Operator.SQUARE)

        assertEquals(11.00, calculator.operating, 0.00)
    }

    @Test
    fun exponential_isCorrect()
    {
        calculator.operating = 11.00

        calculator.performOperation(Operator.EXPONENTIAL)

        assertEquals(121.00, calculator.operating, 0.00)
    }

    @Test
    fun memory_store_isCorrect()
    {
        calculator.operating = 11.00

        calculator.performMemoryOperation(Operator.MEMORY_SUM)

        assertEquals(11.00, calculator.memory, 0.00)
    }
}