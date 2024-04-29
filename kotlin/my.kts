fun addTwoNumbers(number1: Int, number2: Int): Int {
    return number1 + number2
}

fun addTenNine(function: (Int, Int) -> Int) {
    val result: Int = function(10, 9)
    println("결과는 $result 입니다")
}

addTenNine(::addTwoNumbers)

val add2: (Int, Int) -> Int = { number1: Int, number2: Int ->
    number1 + number2
}
addTenNine(add2)

val add3: (Int, Int) -> Int = {number1, number2 ->
    number1 + number2
}
addTenNine(add3)
val add4 = { number1: Int, number2: Int ->
    number1 + number2
}

addTenNine {number1, number2 -> number1 + number2}

val add5: () -> Int = {
    10 + 9
}

val add6: (Int) -> Int = {
    10 + it
}