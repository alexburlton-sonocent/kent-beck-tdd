package money

import money.Currency
import money.Expression
import java.util.*

class Bank {
    private val rates = Hashtable<Pair<Currency, Currency>, Int>()

    fun addRate(from: Currency, to: Currency, rate: Int) {
        rates[Pair(from, to)] = rate
    }

    fun rate(from: Currency, to: Currency): Int {
        if (from == to) return 1
        return rates[Pair(from, to)] ?: throw Exception("No rate found for $from -> $to")
    }

    fun reduce(source: Expression, currency: Currency) = source.reduce(this, currency)
}