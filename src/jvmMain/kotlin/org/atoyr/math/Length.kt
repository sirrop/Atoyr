package org.atoyr.math

actual interface Length {
    actual fun convertTo(unit: Units): Length
    actual fun nanometres(): Length
    actual fun micrometres(): Length
    actual fun millimetres(): Length
    actual fun metres(): Length
    actual fun centimetres(): Length
    actual fun kilometres(): Length
    actual fun inches(): Length
    actual fun feet(): Length
    actual fun yards(): Length
    actual fun mile(): Length
    actual fun angstrom(): Length
    actual fun value(): Double
    actual fun unit(): Units
    actual fun decValue(): BigDecimal

    actual operator fun plus(other: Byte): Length
    actual operator fun plus(other: Short): Length
    actual operator fun plus(other: Int): Length
    actual operator fun plus(other: Float): Length
    actual operator fun plus(other: Double): Length
    actual operator fun plus(other: BigInteger): Length
    actual operator fun plus(other: BigDecimal): Length
    actual operator fun plus(other: Length): Length

    actual operator fun minus(other: Byte): Length
    actual operator fun minus(other: Short): Length
    actual operator fun minus(other: Int): Length
    actual operator fun minus(other: Float): Length
    actual operator fun minus(other: Double): Length
    actual operator fun minus(other: BigInteger): Length
    actual operator fun minus(other: BigDecimal): Length
    actual operator fun minus(other: Length): Length

    actual operator fun times(other: Byte): Length
    actual operator fun times(other: Short): Length
    actual operator fun times(other: Int): Length
    actual operator fun times(other: Float): Length
    actual operator fun times(other: Double): Length
    actual operator fun times(other: BigInteger): Length
    actual operator fun times(other: BigDecimal): Length

    actual operator fun div(other: Byte): Length
    actual operator fun div(other: Short): Length
    actual operator fun div(other: Int): Length
    actual operator fun div(other: Float): Length
    actual operator fun div(other: Double): Length
    actual operator fun div(other: BigInteger): Length
    actual operator fun div(other: BigDecimal): Length

    actual operator fun inc(): Length
    actual operator fun dec(): Length

    actual operator fun unaryPlus(): Length
    actual operator fun unaryMinus(): Length

    actual operator fun compareTo(other: Length): Int
    actual enum class Units {
        NANOMETRES, MICROMETRES, MILLIMETRES, CENTIMETRES, METRES, KILOMETRES, INCHES, FEET, YARDS, MILES, ANGSTROMS;

        actual fun coefficientTo(unit: Units): Double = unit.scale() / scale()

        actual fun decCoefficientTo(unit: Units): BigDecimal = unit.decScale() / decScale()

        private fun scale(): Double = when (this) {
            NANOMETRES -> 0.000000001
            MICROMETRES -> 0.000001
            MILLIMETRES -> 0.001
            CENTIMETRES -> 0.1
            METRES -> 1.0
            KILOMETRES -> 1000.0
            INCHES -> 0.0254
            FEET -> 0.3048
            YARDS -> 0.9144
            MILES -> 1609.344
            ANGSTROMS -> 0.0000000001
        }

        private fun decScale(): BigDecimal = when (this) {
            NANOMETRES -> BigDecimal("0.000000001")
            MICROMETRES -> BigDecimal("0.000001")
            MILLIMETRES -> BigDecimal("0.001")
            CENTIMETRES -> BigDecimal("0.1")
            METRES -> BigDecimal("1.0")
            KILOMETRES -> BigDecimal("1000.0")
            INCHES -> BigDecimal("0.0254")
            FEET -> BigDecimal("0.3048")
            YARDS -> BigDecimal("0.9144")
            MILES -> BigDecimal("1609.344")
            ANGSTROMS -> BigDecimal("0.0000000001")
        }
    }

}