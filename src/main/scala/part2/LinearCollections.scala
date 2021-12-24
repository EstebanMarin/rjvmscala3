package part2

object LinearCollections:
  // Sequence
  object Sequence:
    val aSequence = Seq(1, 2, 3)
    // usefull API
    val head = aSequence.head
    val tail = aSequence.tail
    val reversed = aSequence.reverse
    val concatenated = aSequence ++ Seq(1, 2, 3)
    val sortedSequence = aSequence.sorted
    // map, flatMap, filter
    val anIncrementedSequence = aSequence.map(_ + 1)
    val aFlatedSequence = aSequence.flatMap(x => Seq(x, x + 1))
    val aFilteredSequence = aSequence.filter(_ % 2 == 0)
  end Sequence

  object ScalaList:
    // Same as Seq
    val aList = List(1, 2, 3)
    // appending and prependding
    val aBiggerList = 0 +: aList :+ 4

  // List
  @main def linearMain =
    // println(Sequence.aFlatedSequence)
    println(ScalaList.aBiggerList)
