package money

import money.Money.Companion.dollar
import money.Money.Companion.franc
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.Test

class Tests {

    @Test
    fun testMultiplication() {
        val five = dollar(5)
        five.times(2) shouldBe dollar(10)
        five.times(3) shouldBe dollar(15)
    }

    @Test
    fun testEquality() {
        dollar(5) shouldBe dollar(5)
        dollar(5) shouldNotBe dollar(6)

        franc(5) shouldNotBe dollar(5)
    }

    @Test
    fun testCurrency() {
        dollar(1).currency() shouldBe Currency.USD
        franc(1).currency() shouldBe Currency.CHF
    }

    @Test
    fun testSimpleAddition() {
        val sum = dollar(5).plus(dollar(5))
        val bank = Bank()
        bank.reduce(sum, Currency.USD) shouldBe dollar(10)
    }

    @Test
    fun testReduceSum() {
        val sum = Sum(dollar(3), dollar(4))
        val result = Bank().reduce(sum, Currency.USD)
        result shouldBe dollar(7)
    }

    @Test
    fun testReduceMoney() {
        Bank().reduce(dollar(1), Currency.USD) shouldBe dollar(1)
    }

    @Test
    fun testIdentityRate() {
        Bank().rate(Currency.USD, Currency.USD) shouldBe 1
    }

    @Test
    fun testReduceMoneyDifferentCurrency() {
        val bank = Bank()
        bank.addRate(Currency.CHF, Currency.USD, 2)
        val result = bank.reduce(franc(2), Currency.USD)
        result shouldBe dollar(1)
    }

    @Test
    fun testMixedAddition() {
        val bank = Bank()
        bank.addRate(Currency.CHF, Currency.USD, 2)
        val result = bank.reduce(dollar(5).plus(franc(10)), Currency.USD)
        result shouldBe dollar(10)
    }

    @Test
    fun testSumPlusMoney() {
        val bank = Bank()
        bank.addRate(Currency.CHF, Currency.USD, 2)

        val sum = Sum(dollar(5), franc(10)).plus(dollar(5))

        val result = bank.reduce(sum, Currency.USD)
        result shouldBe dollar(15)
    }

    @Test
    fun testSumTimes() {
        val sum = Sum(dollar(5), dollar(5)).times(2)
        val result = Bank().reduce(sum, Currency.USD)
        result shouldBe dollar(20)
    }

}