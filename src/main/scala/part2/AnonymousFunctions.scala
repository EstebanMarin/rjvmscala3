package part2

object AnonymousFunctions:
  val doubler: Int => Int = new Function[Int, Int]:
    override def apply(x: Int) = x * 2

  val doubler_v2: Int => Int = x => x * 2

  val stringToInt = { (string: String) =>
    string.toInt
  }
end AnonymousFunctions
