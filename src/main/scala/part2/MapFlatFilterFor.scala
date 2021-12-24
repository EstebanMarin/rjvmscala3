package part2

import part2.HofCurrying.Cons

object MapFlatFilterFor:
  val aList = List(1, 2, 3)
  val firstElement: Int = aList.head
  val tailofList: List[Int] = aList.tail

  // all combinations i.e: 1a - black
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "yellow")

  val testProgram =
    for
      n <- numbers if n % 2 == 0
      l <- chars
      c <- colors
    yield s"$n$l - $c"

  @main def mfffmain =
    println("from mfffmain")
    println(testProgram)
end MapFlatFilterFor
