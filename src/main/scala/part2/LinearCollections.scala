package part2

object LinearCollections:
  val aSequence = Seq(1, 2, 3)
  // usefull API
  val reversed = aSequence.reverse
  val concatenated = aSequence ++ Seq(1, 2, 3)
  val sortedSequence = aSequence.sorted
  // map, flatMap, filter

  val anIncrementedSequence = aSequence.map(_ + 1)
  val aFlatedSequence = aSequence.flatMap(x => Seq(x, x + 1))
  val aFilteredSequence = aSequence.filter(_ % 2 == 0)
  @main def linearMain =
    println(aFlatedSequence)
