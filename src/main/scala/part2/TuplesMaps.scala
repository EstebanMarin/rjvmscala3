package part2

object TuplesMaps:
  // Finate ordered "Lists" / group of vaules under the same value

  object Tuples:
    val aTuple: (Int, String) = (2, "Esteban")
    val aTupleTwo: (Int, String) = 2 -> "Esteban"
    val aCopied = aTuple.copy(_1 = 32)

  object Maps:
    val aMap = Map()
    val keyValue = Map(
      "Jiim" -> 56,
      "Jim" -> 56,
      "Jm" -> 56
    ).withDefaultValue(-1)

    val hasName = keyValue.contains("Jiim")
    val marysNumberDefaultValue = keyValue("Mary")
    val gettingValue = keyValue("Jim")

    val newPair = "Esteban" -> 35
    val newMap = keyValue + newPair
    //Turning a map into a linear Sequence
    val linearKeyValue = keyValue.toList
    // vice versa, linear sequence to map
    val keyValueAgain = linearKeyValue.toMap

    val names = List("Jim", "Jiim", "jimm", "Esteban")
    val groupByLetter = names.groupBy(name => name.charAt(0))

  object Excersises:
    /*
    Social Network domain modeling = Map[]
     */
    type PersonInfo = Set[String]
    type Name = String
    type Network = Map[Name, PersonInfo]
    def addPerson(
        network: Network,
        newPerson: Name
    ): Network =
      network + (newPerson -> Set())

    def removePerson(
        network: Network,
        person: Name
    ): Network =
      network - person

    def addFriend(
        network: Network,
        personA: Name,
        personB: Name
    ): Network =
      val friendsA: Set[Name] = network.apply(personA) + personB
      val friendsB: Set[Name] = network.apply(personB) + personA
      network + (personA -> friendsA) + (personB -> friendsB)

    def unfriend(
        network: Network,
        person: Name,
        newFriend: Name
    ): Network =
      val updatedFriendsList: Set[Name] = network.apply(person) - newFriend
      network - person + (person -> updatedFriendsList)

    def numberOfFriends(
        network: Network,
        person: Name
    ): Int =
      network.apply(person).size

  @main def TuplesMain =
    println(Maps.hasName)
    println(Maps.marysNumberDefaultValue)
    println(Maps.gettingValue)
    println(Maps.newMap)
    println(Maps.groupByLetter)
end TuplesMaps
