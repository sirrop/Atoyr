package org.atoyr.math

expect interface Length {
    fun convertTo(unit: Units): Length

    fun nanometres(): Length
    fun micrometres(): Length
    fun millimetres(): Length
    fun metres(): Length
    fun centimetres(): Length
    fun kilometres(): Length

    fun inches(): Length
    fun feet(): Length
    fun yards(): Length
    fun mile(): Length

    fun angstrom(): Length

    fun value(): Double
    fun unit(): Units
    fun decValue(): BigDecimal

    operator fun plus(other: Byte): Length
    operator fun plus(other: Short): Length
    operator fun plus(other: Int): Length
    operator fun plus(other: Float): Length
    operator fun plus(other: Double): Length
    operator fun plus(other: BigInteger): Length
    operator fun plus(other: BigDecimal): Length
    operator fun plus(other: Length): Length

    operator fun minus(other: Byte): Length
    operator fun minus(other: Short): Length
    operator fun minus(other: Int): Length
    operator fun minus(other: Float): Length
    operator fun minus(other: Double): Length
    operator fun minus(other: BigInteger): Length
    operator fun minus(other: BigDecimal): Length
    operator fun minus(other: Length): Length

    operator fun times(other: Byte): Length
    operator fun times(other: Short): Length
    operator fun times(other: Int): Length
    operator fun times(other: Float): Length
    operator fun times(other: Double): Length
    operator fun times(other: BigInteger): Length
    operator fun times(other: BigDecimal): Length

    operator fun div(other: Byte): Length
    operator fun div(other: Short): Length
    operator fun div(other: Int): Length
    operator fun div(other: Float): Length
    operator fun div(other: Double): Length
    operator fun div(other: BigInteger): Length
    operator fun div(other: BigDecimal): Length

    operator fun inc(): Length
    operator fun dec(): Length

    operator fun unaryPlus(): Length
    operator fun unaryMinus(): Length

    operator fun compareTo(other: Length): Int

    enum class Units {
        NANOMETRES,
        MICROMETRES,
        MILLIMETRES,
        CENTIMETRES,
        METRES,
        KILOMETRES,

        INCHES,
        FEET,
        YARDS,
        MILES,

        ANGSTROMS;
        fun coefficientTo(unit: Units): Double
        fun decCoefficientTo(unit: Units): BigDecimal
    }
}