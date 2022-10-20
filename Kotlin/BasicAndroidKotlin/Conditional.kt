fun main() {
    val myFirstDice = Dice(6)
    val rollResult = myFirstDice.roll()
    val luckyNumber = 4
    
    // if��
    if(rollResult == luckyNumber){
        println("Lucky Number!!!")
    } else {
        println("Your number is $rollResult")
    }

    //when ��
    when(rollResult) {
        1 -> println("1�ʶ� �Ⱥ��̸�~")
        2 -> println("2���� �����ѵ�~")
        3 -> println("3�ʴ� ��� ��ٷ�~")
        4 -> println("4���� �λ����~")
        5 -> println("5���� ���Ұž�~")
        6 -> println("6�ʾ� �������� �θ�����~")
        else -> println("��..Ű?....")
    }
}

// Dice Ŭ���� -> ������ ���ڸ� �޾� �ش� ���� ũ���� �ֻ��� ����
class Dice(val numSides: Int) {
    // ������ ���� �� ���� ����
    fun roll(): Int {
        // 1 ~ numSides -> ��������
        return (1..numSides).random()
    }
}