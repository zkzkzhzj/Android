// 컬렉션 - 유사한 데이터 유형 항목 집합을 단일 단위로 나타내는 클래스

fun main() {
    // list
    val numbers = listOf(9, 3, 2, 5, 5, 1, 9, 3, 7, 4)
    val sortedNumbers = numbers.sorted()
    println("Numbers: $numbers")
    println("Sort Numbers: $sortedNumbers")

    // set
    // 중복불가
    val setOfNumbers = numbers.toSet()
    println("set: ${setOfNumbers}")
    // 순서가 다르다 하더라도 동일함
    val immutableSet = setOf(1, 2, 3)
    val mutableSet = mutableSetOf(3, 2, 1)
    println(immutableSet == mutableSet)
    // 특정 값이 있는지 확인
    println("contains 7: ${setOfNumbers.contains(7)}")

    // map
    // 값을 키와 연결
    val peopleAges: MutableMap<String, Int> = mutableMapOf(
        "Faang" to 27,
        "zkzkzhzj" to 31
    )
    println(peopleAges)
    // 추가
    peopleAges.put("Ghost", 55)
    // 약식 표기법
    peopleAges["bungju"] = 18
    println(peopleAges)
    // 값 변경
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
    마지막 값에 쉼표가 붙지 않게 출력
    컬렉션 map이 아닌 method map
    작성한 함수의 결과에 대한 컬렉션을 만든다
    zkzkzhzj=30 을 zkzkzhzj is 30 으로 바꾸며 마지막 컬렉션 항목을 제외한
    컬렉션 사이에 쉼표를 넣도록 동작
     */
    println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", ") )

    // filter - 특정 조건과 일치하는 항목 찾기
    // peopleAges 맵 내부에 key 글자수가 3글자 이하인 값
    val filteredNames = peopleAges.filter { it.key.length < 6 }
    println(filteredNames)

    // 람다 및 고차함수
    /*
    람다
    함수 이름 다음에 매개변수가 포함된 괄호를 사용하는 대신
    함수 이름 다음에 { } 를 사용한 것
    또한 { } 안에 작은 함수를 작성하지만 이름은 존재하지 않는다.
    이름이 없으며 바로 표현식으로 사용할 수 있는 함수를 람다 표현식, 람다라고 한다
    ex) forEach { }, map { }
    */
    /*
    코틀린에서는 함수 유형이라는 것이 제공
    입력 매개변수와 반환 값을 기반으로 특정 유형의 함수 정의 가능
    ex) (Int) -> Int  : 매개변수는 Int 형에 반환 값도 Int 형
    */
    // 함수명: 함수 타입 = 람다식
    val triple: (Int) -> Int = { a: Int -> a * 3 }
    println(triple(3))
    // 람다식 내부의 명시적인 선언을 생략할 수도 있다
    // 코틀린에서는 람다내부에 단일 매개변수가 있다면 특수 식별자 it사용
    val triple2: (Int) -> Int = { it * 3 }
    println(triple2(3))

    /*  
    고차함수
    함수를 다른 함수로 전달하거나 함수를 반환하는 것을 의미
    ex) map, filter, forEach 등
    */
    // sortedWith() 고차함수
    // 문자열의 길이를 기준으로 목록 정렬, 두 문자열의 길이를 가져와 비교
    val peopleNames = listOf("Fred", "Ann", "Barbara", "Joe", "Kelly", "King")
    println(peopleNames.sorted())
    println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length } )
    /*
    원리
    sortedWith 매개변수 str1, str2 가 있다. 이후 함수 본문 등장
    람다의 마지막 표현식은 반환 값
    위 경우 str1 길이와 str2 길이의 차이(Int형) 반환
    sortedWith() 함수는 str1, str2 사이 비교를 한번에 처리하고
    길이 기준으로 오름차순 목록 출력
    */

    // 단어 목록 만들기
    val words = listOf("about", "acute", "awesome", "balloon", "best", "brief", "class", "coffee", "creative")
    // B로 시작하는 단어의 컬렉션 가져오기, ignoreCase(대소문자 무시하기 설정)
    val filteredWords = words.filter { it.startsWith("b", ignoreCase = true) }
    println(filteredWords)
    // 위 필터된 컬렉션을 순서를 무작위로 변경
    println(filteredWords.shuffled())
    // 모든 단어가 아닌 일부분만 필요하다면
    println(filteredWords.take(2))
    // 무작위로 2개의 단어
    println(filteredWords.shuffled().take(2))
}