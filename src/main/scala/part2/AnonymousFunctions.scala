package part2

object AnonymousFunctions:
  val doubler: Int => Int = new Function[Int, Int]:
    override def apply(x: Int) = x * 2

  val doubler_v2: Int => Int = x => x * 2

  val stringToInt = { (string: String) =>
    string.toInt
  }

  val doubler_v3: Int => Int = _ * 2
  val adder: (Int, Int) => Int = _ + _

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) =
    ???

end AnonymousFunctions
