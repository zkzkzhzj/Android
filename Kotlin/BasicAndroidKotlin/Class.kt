import kotlin.math.PI
import kotlin.math.sqrt

/*
목표 : 클래스 계층 구조에 대한 이해
abstract(추상) 클래스에 대한 이해와 인스턴스화 할 수 없음
서브 클래스를 만드는 방법
상위 클래스의 메소드를 재정의 하는 법(override)
super 키워드를 사용하여 상위 클래스를 참조
open 키워드 사용
private 사용하여 범위 지정
with 구문에 대한 이해
kotlin 라이브러리 기능 가져오기(import)
*/

fun main() {
    val squareCabin = SquareCabin(6, 50.0)
    val roundHut = RoundHut(3, 10.0)
    val roundTower = RoundTower(4, 15.5)
/*    
    println("Square Carbin")
    println("Capacity : ${squareCabin.capacity}")
    println("Material : ${squareCabin.buildingMaterial}")
    println("Has Room? : ${squareCabin.hasRoom()}")
    println("Floor area: ${floorArea()}")
    with 를 사용하여 단순화
*/
// 해당 인스턴스 객체를 사용하여 모두 실행
    with(squareCabin) {
        println("Square Carbin")
        println("Capacity : ${capacity}")
        println("Material : ${buildingMaterial}")
        println("Has Room? : ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }

    with(roundHut) {
        println("Round Hut")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Floor area: ${floorArea()}")
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Carpet Length: ${calculateMaxCarpetLength()}")
    }

    with(roundTower) {
        println("Round Tower=")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }
}

/* 
부모 클래스, 슈퍼 클래스 <-> 자식 클래스, 서브 클래스
추상 클래스 : 완전히 구현되지 않아 인스턴스화할 수 없는 클래스(스케치 개념)
*/
// 추상클래스
// 주거지(실제 입주할 주민 수 - 해당 클래스 내부에서만 사용할 수 있게 private 설정)
abstract class Dwelling(private var residents: Int) {
    // 값을 지정할 수 없기 때문에 abstract 설정
    // 건축재료
    abstract val buildingMaterial: String
    // 수용가능한 인원 수
    abstract val capacity: Int

    // 입주가능한지 여부
    fun hasRoom(): Boolean {
        return residents < capacity
    }

    // 면적 계산
    abstract fun floorArea(): Double

    // 입주자 추가
    fun getRoom() {
        if(capacity > residents) {
            residents++
            println("You god a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
}

// 지역 등록 (입주자 수를 받아와 슈퍼클래스에 전송)
class SquareCabin(residents: Int, val length: Double): Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length* length
    }
}
// 코틀린에선 기본 클래스를 슈퍼 클래스로 둘수가 없기 때문에 abstract 키워드나 open 키워드를 사용
open class RoundHut(residents: Int, val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

    override fun floorArea(): Double {
        return PI * radius * radius
    }

    fun calculateMaxCarpetLength(): Double {
        return sqrt(2.0) * radius
    }
}

class RoundTower(residents: Int, radius: Double, val floors: Int = 2) : RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = floors * 4

    override fun floorArea(): Double {
        // return PI * radius * radius * floors 부모 메소드를 가져와 작업 가능
        return super.floorArea() * floors
    }
}