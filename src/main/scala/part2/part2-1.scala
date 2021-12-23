import scala.annotation.tailrec
object CaseClasses2:

  abstract class LList[A]:
    def head: A
    def tail: LList[A]
    val isEmpty: Boolean
    infix def +(element: A): LList[A] = new NonEmpty(element, this)
    infix def ++(anotherList: LList[A]): LList[A]
    def map[B](transformer: A => B): LList[B]
    def filter(predicate: A => Boolean): LList[A]
    def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]

  case class Empty[A]() extends LList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: LList[A] = throw new NoSuchElementException
    override val isEmpty: Boolean = true
    override def map[B](transformer: A => B): LList[B] =
      Empty()
    override def filter(predicate: A => Boolean): LList[A] =
      this

    override def ++(anotherList: LList[A]): LList[A] = anotherList
    override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] =
      Empty()

  }

  case class NonEmpty[A](override val head: A, override val tail: LList[A])
      extends LList[A]:
    override val isEmpty = false
    override def map[B](transformer: A => B): LList[B] =
      NonEmpty(transformer(head), tail.map(transformer))

    override def filter(predicate: A => Boolean): LList[A] =
      if (predicate(head)) new NonEmpty(head, tail.filter(predicate))
      else tail.filter(predicate)

    override def ++(anotherList: LList[A]): LList[A] =
      NonEmpty(head, tail ++ anotherList)
    override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] =
      transformer.transform(head) ++ tail.flatMap(transformer)

    override def toString =
      @tailrec
      def concatenateElements(reminder: LList[A], acc: String): String =
        if (reminder.isEmpty) acc
        else concatenateElements(reminder.tail, s"${acc} ${reminder.head}")

      s"[${concatenateElements(this, "")} ]"

  trait Predicate[T]:
    def test(x: T): Boolean

  class EvenPredicate extends Predicate[Int]:
    override def test(x: Int) =
      x % 2 == 0

  trait Transformer[A, B]:
    def transform(x: A): B

  class Doubler extends Transformer[Int, Int] {
    override def transform(x: Int) = x * 2
  }

  val doubler: Int => Int = _ * 2

  class DoublerList extends Transformer[Int, LList[Int]] {
    override def transform(x: Int): LList[Int] =
      new NonEmpty(x, new NonEmpty(x + 1, Empty()))
  }

  val listofInterger: LList[Int] =
    new Empty + 1 + 2 + 3

  val supperAdder: Function1[Int, Function1[Int, Int]] = new Function1 {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int) = x + y
    }
  }

  val add2 = supperAdder(2)

  val result = add2(2)

  def main(args: Array[String]): Unit = {}
