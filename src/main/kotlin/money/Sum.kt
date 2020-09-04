package money

class Sum(private val augend: Expression, private val addend: Expression):
    Expression {
    override fun reduce(bank: Bank, to: Currency): Money {
        val amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount
        return Money(amount, to)
    }

    override fun times(multiplier: Int) =
        Sum(augend.times(multiplier), addend.times(multiplier))
}