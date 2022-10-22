import kotlin.math.PI
import kotlin.math.sqrt

/*
��ǥ : Ŭ���� ���� ������ ���� ����
abstract(�߻�) Ŭ������ ���� ���ؿ� �ν��Ͻ�ȭ �� �� ����
���� Ŭ������ ����� ���
���� Ŭ������ �޼ҵ带 ������ �ϴ� ��(override)
super Ű���带 ����Ͽ� ���� Ŭ������ ����
open Ű���� ���
private ����Ͽ� ���� ����
with ������ ���� ����
kotlin ���̺귯�� ��� ��������(import)
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
    with �� ����Ͽ� �ܼ�ȭ
*/
// �ش� �ν��Ͻ� ��ü�� ����Ͽ� ��� ����
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
�θ� Ŭ����, ���� Ŭ���� <-> �ڽ� Ŭ����, ���� Ŭ����
�߻� Ŭ���� : ������ �������� �ʾ� �ν��Ͻ�ȭ�� �� ���� Ŭ����(����ġ ����)
*/
// �߻�Ŭ����
// �ְ���(���� ������ �ֹ� �� - �ش� Ŭ���� ���ο����� ����� �� �ְ� private ����)
abstract class Dwelling(private var residents: Int) {
    // ���� ������ �� ���� ������ abstract ����
    // �������
    abstract val buildingMaterial: String
    // ���밡���� �ο� ��
    abstract val capacity: Int

    // ���ְ������� ����
    fun hasRoom(): Boolean {
        return residents < capacity
    }

    // ���� ���
    abstract fun floorArea(): Double

    // ������ �߰�
    fun getRoom() {
        if(capacity > residents) {
            residents++
            println("You god a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
}

// ���� ��� (������ ���� �޾ƿ� ����Ŭ������ ����)
class SquareCabin(residents: Int, val length: Double): Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length* length
    }
}
// ��Ʋ������ �⺻ Ŭ������ ���� Ŭ������ �Ѽ��� ���� ������ abstract Ű���峪 open Ű���带 ���
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
        // return PI * radius * radius * floors �θ� �޼ҵ带 ������ �۾� ����
        return super.floorArea() * floors
    }
}