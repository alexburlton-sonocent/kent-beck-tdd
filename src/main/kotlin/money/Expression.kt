package money

interface Expression {
    fun reduce(bank: Bank, to: Currency): Money
    fun times(multiplier: Int): Expression

    fun plus(addend: Expression): Expression = Sum(this, addend)
}