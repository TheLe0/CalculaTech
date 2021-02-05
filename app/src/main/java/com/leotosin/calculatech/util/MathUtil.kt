package com.leotosin.calculatech.util

object MathUtil
{
    fun factorial(num: Int) = (1..num).reduce(Int::times)
}