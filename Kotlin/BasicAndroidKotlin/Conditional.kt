fun main() {
    val myFirstDice = Dice(6)
    val rollResult = myFirstDice.roll()
    val luckyNumber = 4
    
    // if문
    if(rollResult == luckyNumber){
        println("Lucky Number!!!")
    } else {
        println("Your number is $rollResult")
    }

    //when 문
    when(rollResult) {
        1 -> println("1초라도 안보이면~")
        2 -> println("2렇게 초초한데~")
        3 -> println("3초는 어떻게 기다려~")
        4 -> println("4랑해 널사랑해~")
        5 -> println("5늘은 말할거야~")
        6 -> println("6십억 지구에서 널만난건~")
        else -> println("럭..키?....")
    }
}

// Dice 클래스 -> 임의의 숫자를 받아 해당 숫자 크기의 주사위 생성
class Dice(val numSides: Int) {
    // 임의의 범위 내 숫자 리턴
    fun roll(): Int {
        // 1 ~ numSides -> 랜덤돌림
        return (1..numSides).random()
    }
}