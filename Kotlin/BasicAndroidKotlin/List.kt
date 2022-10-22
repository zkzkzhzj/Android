fun main() {
    // 불변 리스트
    val numbers: List<Int> = listOf(3, 5, 4, 1, 2, 6)

    println(numbers)

    // 인덱스 값에 따른 출력
    println("First element: = ${numbers[0]}")
    // 목록
    println("First: ${numbers.first()}")
    println("Last: ${numbers.last()}")
    // 해당 값이 있는지 확인
    println("Contains 4? ${numbers.contains(4)}")
    println("Contains 7? ${numbers.contains(7)}")
    // 역순
    println("Reversed list: ${numbers.reversed()}")
    // 정렬
    println("Sorted list: ${numbers.sorted()}")
    
    // 변할 수 있는 리스트
    val entrees: MutableList<String> = mutableListOf()
    println("Entrees: $entrees")
    // 추가
    entrees.add("noodles")
    println("Entrees: $entrees")
    entrees.add("spaghetti")
    println("Entrees: $entrees")
    // 모든 항목 추가
    val moreItems = listOf("ravioli", "lasagna", "fettuccine")
    // 항목 추가에 성공하면 return 값 boolean 타입 있음
    entrees.addAll(moreItems)
    println("Entrees: $entrees")
    val sortEntrees = entrees.sorted()
    println("Entrees: $sortEntrees")
    // 삭제, 삭제 또한 리턴값이 존재한다
    entrees.remove("noodles")
    println("Entrees: $entrees")
    println("Remove: ${entrees.remove("ppo")}")
    // 특정 인덱스에 대한 값 삭제
    entrees.removeAt(2)
    println("Entrees: $entrees")
    // 모두 삭제
    entrees.clear()
    println("Entrees: $entrees")
    // 리스트가 비어있는지 확인
    println("Empty? ${entrees.isEmpty()}")
    entrees.addAll(moreItems)
    println("Entrees: $entrees")
    println("Empty? ${entrees.isEmpty()}")

    /*
    while loop
    true 라면 다시한번 반복, false 일경우 반복을 벗어남
    총 손님의 숫자 계산
     */
    val guestsPerFamily = listOf(2, 4, 1, 3)
    // 잊지말자. var(가변) val(불변)
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
    // 해당 텍스트의 문자 수 출력
    for(num in count){
        println("$num - Number of characters: ${num.length}")
    }
}