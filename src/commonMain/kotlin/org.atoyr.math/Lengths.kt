package org.atoyr.math

expect fun nanometresOf(len: Double): Length
expect fun micrometresOf(len: Double): Length
expect fun millimetresOf(len: Double): Length
expect fun centimetresOf(len: Double): Length
expect fun metresOf(len: Double): Length
expect fun kilometresOf(len: Double): Length
expect fun inchesOf(len: Double): Length
expect fun feetOf(len: Double): Length
expect fun yardsOf(len: Double): Length
expect fun milesOf(len: Double): Length
expect fun angstromsOf(len: Double): Length

expect fun bigLengthOf(len: BigInteger, units: Length.Units): Length