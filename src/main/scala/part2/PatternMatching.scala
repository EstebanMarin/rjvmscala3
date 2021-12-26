package part2

object PatternMatching:
  sealed trait Exp
  case class Number(n: Int) extends Exp
  case class Sum(e1: Exp, e2: Exp) extends Exp
  case class Prod(e1: Exp, e2: Exp) extends Exp

  def show(expr: Exp): String = expr match {
    case Number(n)        => s"$n"
    case Sum(left, right) => s"${show(left)} + ${show(right)}"
    case Prod(left, right) => {
      def maybeShow(expr: Exp) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_)  => show(expr)
        case Sum(_, _)  => s"(${show(expr)})"
      }
      s"${maybeShow(left)} * ${maybeShow(right)}"
    }
  }

  val n3: Number = Number(3)
  val n4: Number = Number(4)
  val sum: Sum = Sum(n3, n4)
  val another = Sum(n3, sum)

  @main def PatternMain =
    println(show(sum))
    println(show(another))
