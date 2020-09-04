package money

data class Money(val amount: Int, private val currency: Currency): Expression {
    override fun times(multiplier: Int): Expression = Money(amount * multiplier, currency)

    fun currency() = currency

    override fun reduce(bank: Bank, to: Currency): Money {
        val rate = bank.rate(currency, to)
        return Money(amount / rate, to)
    }

    companion object {
        fun dollar(amount: Int): Money = Money(amount, Currency.USD)
        fun franc(amount: Int): Money = Money(amount, Currency.CHF)
    }
}