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
    val aFilteredSequence = aSequence.filter(_ % 2 == 0)
    val aFlatedSequence = aSequence.flatMap(x => Seq(x, x + 1))
    val sum = aSequence.foldLeft(0)(_ + _)
    val stringRep = aSequence.mkString("[", ",", "]")
  end Sequence

  object Lists:
    // Same as Seq
    val aList = List(1, 2, 3)
    // appending and prependding
    val aBiggerList = 0 +: aList :+ 4
    val prepending = 0 :: aList
    val scalax5 = List.fill(5)("Scala")
  end Lists

  object Ranges:
    val aRange = 1 to 10
    (1 to 10).foreach(_ => println("Scala"))
  end Ranges

  object Arrays:
    // main capability is mutable
    val anArray = Array(1, 2, 3, 4, 5, 6)
    val anSequence = anArray.toIndexedSeq
    anArray.update(2, 22)
  end Arrays

  object Vectors:
    // fast Sequ for large amount of data
    val aVector = Vector(1, 2, 3, 4, 5, 6)
  end Vectors

  object Sets:
    //main API test if it is in Set
    val aSet = Set(1, 2, 3, 4, 5, 6) // no garanties of ordering
    val contains = aSet.contains(3)
    // removing from set
    val reducedSet = aSet - 4
    val biggerSet = aSet + 4
    val concatenated = aSet ++ Set(8, 9, 75, 8)
    val anotherSet = Set(8, 9, 75, 8)
    val concatenated_v2 = aSet | anotherSet
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_V2 = aSet -- anotherSet
    // intersection
    val intersecion = aSet.intersect(anotherSet)
    val intersecion_v2 = aSet & anotherSet

  end Sets

  @main def linearMain =
    // println(Sequence.aFlatedSequence)
    println(Lists.aBiggerList)
    println(Sequence.stringRep)
