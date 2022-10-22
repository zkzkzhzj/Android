// �÷��� - ������ ������ ���� �׸� ������ ���� ������ ��Ÿ���� Ŭ����

fun main() {
    // list
    val numbers = listOf(9, 3, 2, 5, 5, 1, 9, 3, 7, 4)
    val sortedNumbers = numbers.sorted()
    println("Numbers: $numbers")
    println("Sort Numbers: $sortedNumbers")

    // set
    // �ߺ��Ұ�
    val setOfNumbers = numbers.toSet()
    println("set: ${setOfNumbers}")
    // ������ �ٸ��� �ϴ��� ������
    val immutableSet = setOf(1, 2, 3)
    val mutableSet = mutableSetOf(3, 2, 1)
    println(immutableSet == mutableSet)
    // Ư�� ���� �ִ��� Ȯ��
    println("contains 7: ${setOfNumbers.contains(7)}")

    // map
    // ���� Ű�� ����
    val peopleAges: MutableMap<String, Int> = mutableMapOf(
        "Faang" to 27,
        "zkzkzhzj" to 31
    )
    println(peopleAges)
    // �߰�
    peopleAges.put("Ghost", 55)
    // ��� ǥ���
    peopleAges["bungju"] = 18
    println(peopleAges)
    // �� ����
    peopleAges["zkzkzhzj"] = 20
    println(peopleAges)

    // forEach
    for(people in peopleAges) {
        print("$people ,")
    }
    println()
    peopleAges.forEach { print("${it.key} is ${it.value} ,") }
    println()

    /*
    ������ ���� ��ǥ�� ���� �ʰ� ���
    �÷��� map�� �ƴ� method map
    �ۼ��� �Լ��� ����� ���� �÷����� �����
    zkzkzhzj=30 �� zkzkzhzj is 30 ���� �ٲٸ� ������ �÷��� �׸��� ������
    �÷��� ���̿� ��ǥ�� �ֵ��� ����
     */
    println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", ") )

    // filter - Ư�� ���ǰ� ��ġ�ϴ� �׸� ã��
    // peopleAges �� ���ο� key ���ڼ��� 3���� ������ ��
    val filteredNames = peopleAges.filter { it.key.length < 6 }
    println(filteredNames)

    // ���� �� �����Լ�
    /*
    ����
    �Լ� �̸� ������ �Ű������� ���Ե� ��ȣ�� ����ϴ� ���
    �Լ� �̸� ������ { } �� ����� ��
    ���� { } �ȿ� ���� �Լ��� �ۼ������� �̸��� �������� �ʴ´�.
    �̸��� ������ �ٷ� ǥ�������� ����� �� �ִ� �Լ��� ���� ǥ����, ���ٶ�� �Ѵ�
    ex) forEach { }, map { }
    */
    /*
    ��Ʋ�������� �Լ� �����̶�� ���� ����
    �Է� �Ű������� ��ȯ ���� ������� Ư�� ������ �Լ� ���� ����
    ex) (Int) -> Int  : �Ű������� Int ���� ��ȯ ���� Int ��
    */
    // �Լ���: �Լ� Ÿ�� = ���ٽ�
    val triple: (Int) -> Int = { a: Int -> a * 3 }
    println(triple(3))
    // ���ٽ� ������ ������� ������ ������ ���� �ִ�
    // ��Ʋ�������� ���ٳ��ο� ���� �Ű������� �ִٸ� Ư�� �ĺ��� it���
    val triple2: (Int) -> Int = { it * 3 }
    println(triple2(3))

    /*  
    �����Լ�
    �Լ��� �ٸ� �Լ��� �����ϰų� �Լ��� ��ȯ�ϴ� ���� �ǹ�
    ex) map, filter, forEach ��
    */
    // sortedWith() �����Լ�
    // ���ڿ��� ���̸� �������� ��� ����, �� ���ڿ��� ���̸� ������ ��
    val peopleNames = listOf("Fred", "Ann", "Barbara", "Joe", "Kelly", "King")
    println(peopleNames.sorted())
    println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length } )
    /*
    ����
    sortedWith �Ű����� str1, str2 �� �ִ�. ���� �Լ� ���� ����
    ������ ������ ǥ������ ��ȯ ��
    �� ��� str1 ���̿� str2 ������ ����(Int��) ��ȯ
    sortedWith() �Լ��� str1, str2 ���� �񱳸� �ѹ��� ó���ϰ�
    ���� �������� �������� ��� ���
    */

    // �ܾ� ��� �����
    val words = listOf("about", "acute", "awesome", "balloon", "best", "brief", "class", "coffee", "creative")
    // B�� �����ϴ� �ܾ��� �÷��� ��������, ignoreCase(��ҹ��� �����ϱ� ����)
    val filteredWords = words.filter { it.startsWith("b", ignoreCase = true) }
    println(filteredWords)
    // �� ���͵� �÷����� ������ �������� ����
    println(filteredWords.shuffled())
    // ��� �ܾ �ƴ� �Ϻκи� �ʿ��ϴٸ�
    println(filteredWords.take(2))
    // �������� 2���� �ܾ�
    println(filteredWords.shuffled().take(2))
}