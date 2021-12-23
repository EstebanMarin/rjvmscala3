import scala.annotation.tailrec

object GenericsMine {
  abstract class LList[A]:
    def head: A
    def tail: LList[A]
    val isEmpty: Boolean
    infix def +(element: A): LList[A] = new NonEmpty(element, this)
    infix def ++(anotherList: LList[A]): LList[A]
    def map[B](transformer: Transformer[A, B]): LList[B]
    def filter(predicate: Predicate[A]): LList[A]
    def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]

  class Empty[A] extends LList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: LList[A] = throw new NoSuchElementException
    override val isEmpty: Boolean = true
    override def map[B](transformer: Transformer[A, B]): LList[B] =
      new Empty[B]
    override def filter(predicate: Predicate[A]): LList[A] =
      this

    override def ++(anotherList: LList[A]): LList[A] = anotherList
    override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] =
      new Empty[B]

  }

  class NonEmpty[A](override val head: A, override val tail: LList[A])
      extends LList[A]:
    override val isEmpty = false
    override def map[B](transformer: Transformer[A, B]): LList[B] =
      new NonEmpty(transformer.transform(head), tail.map(transformer))

    override def filter(predicate: Predicate[A]): LList[A] =
      if (predicate.test(head)) new NonEmpty(head, tail.filter(predicate))
      else tail.filter(predicate)

    override def ++(anotherList: LList[A]): LList[A] =
      new NonEmpty(head, tail ++ anotherList)
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

  class DoublerList extends Transformer[Int, LList[Int]] {
    override def transform(x: Int): LList[Int] =
      new NonEmpty(x, new NonEmpty(x + 1, new Empty))
  }

  val listofInterger: LList[Int] =
    new Empty + 1 + 2 + 3

  @main def genericsMain =
    println("-" * 50)
    val evenPredicate = new Predicate[Int]:
      override def test(x: Int) = x % 2 == 0

    val listofInterger: LList[Int] =
      new Empty + 1 + 2 + 3

    val doubledInt = listofInterger.map(new Doubler)
    println(doubledInt)

    val doubledList = listofInterger.map(new DoublerList)
    println(doubledList)

    val evenNumbers = listofInterger.filter(new EvenPredicate)
    println(evenNumbers)

    println("-" * 50)
}
