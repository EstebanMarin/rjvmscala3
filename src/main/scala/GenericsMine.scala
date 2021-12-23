import scala.annotation.tailrec
object GenericsMine {
  abstract class MyList[T]:
    def head: T
    def tail: MyList[T]
    val isEmpty: Boolean
    infix def +(element: T): MyList[T] = new NonEmpty(element, this)

  class Empty[T] extends MyList[T] {
    override def head: T = throw new NoSuchElementException
    override def tail: MyList[T] = throw new NoSuchElementException
    override val isEmpty: Boolean = true
  }

  class NonEmpty[T](override val head: T, override val tail: MyList[T])
      extends MyList[T]:
    override val isEmpty = false
    override def toString = {
      @tailrec
      def concatenateElements(reminder: MyList[T], acc: String): String =
        if (reminder.isEmpty) acc
        else concatenateElements(reminder.tail, s"${acc} ${reminder.head}")

      s"[${concatenateElements(this, "")}]"
    }

  trait Predicate[T]:
    def test(x: T): Boolean

  class EvenPredicate extends Predicate[Int]:
    override def test(x: Int) =
      x % 2 == 0

  trait Transformer[A, B]:
    def transform(x: A): B

  val listofInterger: MyList[Int] =
    new Empty + 1 + 2 + 3

  @main def genericsMain =
    println("-" * 50)
    val evenPredicate = new Predicate[Int] {
      override def test(x: Int) = x % 2 == 0
    }
    println("-" * 50)
}
