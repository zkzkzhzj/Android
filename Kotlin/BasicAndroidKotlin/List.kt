fun main() {
    // �Һ� ����Ʈ
    val numbers: List<Int> = listOf(3, 5, 4, 1, 2, 6)

    println(numbers)

    // �ε��� ���� ���� ���
    println("First element: = ${numbers[0]}")
    // ���
    println("First: ${numbers.first()}")
    println("Last: ${numbers.last()}")
    // �ش� ���� �ִ��� Ȯ��
    println("Contains 4? ${numbers.contains(4)}")
    println("Contains 7? ${numbers.contains(7)}")
    // ����
    println("Reversed list: ${numbers.reversed()}")
    // ����
    println("Sorted list: ${numbers.sorted()}")
    
    // ���� �� �ִ� ����Ʈ
    val entrees: MutableList<String> = mutableListOf()
    println("Entrees: $entrees")
    // �߰�
    entrees.add("noodles")
    println("Entrees: $entrees")
    entrees.add("spaghetti")
    println("Entrees: $entrees")
    // ��� �׸� �߰�
    val moreItems = listOf("ravioli", "lasagna", "fettuccine")
    // �׸� �߰��� �����ϸ� return �� boolean Ÿ�� ����
    entrees.addAll(moreItems)
    println("Entrees: $entrees")
    val sortEntrees = entrees.sorted()
    println("Entrees: $sortEntrees")
    // ����, ���� ���� ���ϰ��� �����Ѵ�
    entrees.remove("noodles")
    println("Entrees: $entrees")
    println("Remove: ${entrees.remove("ppo")}")
    // Ư�� �ε����� ���� �� ����
    entrees.removeAt(2)
    println("Entrees: $entrees")
    // ��� ����
    entrees.clear()
    println("Entrees: $entrees")
    // ����Ʈ�� ����ִ��� Ȯ��
    println("Empty? ${entrees.isEmpty()}")
    entrees.addAll(moreItems)
    println("Entrees: $entrees")
    println("Empty? ${entrees.isEmpty()}")

    /*
    while loop
    true ��� �ٽ��ѹ� �ݺ�, false �ϰ�� �ݺ��� ���
    �� �մ��� ���� ���
     */
    val guestsPerFamily = listOf(2, 4, 1, 3)
    // ��������. var(����) val(�Һ�)
    var index = 0
    var totalGuest = 0
    while(index < guestsPerFamily.size) {
        totalGuest += guestsPerFamily[index]
        index++
    }
    println("totalGuest: $totalGuest")

    // for loop
    val count = listOf("one", "two", "three", "four")
    for(num in count){
        println(num)
    }
    // �ش� �ؽ�Ʈ�� ���� �� ���
    for(num in count){
        println("$num - Number of characters: ${num.length}")
    }
}