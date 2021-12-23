import scala.annotation.tailrec
import GenericsMine.MyList
object GenericsMine {
  abstract class MyList[A]:
    def head: A
    def tail: MyList[A]
    val isEmpty: Boolean
    infix def +(element: A): MyList[A] = new NonEmpty(element, this)
    def map[B](transformer: Transformer[A, B]): MyList[B]

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
    override val isEmpty: Boolean = true

    override def map[B](transformer: Transformer[A, B]): MyList[B] =
      new Empty[B]
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A])
      extends MyList[A]:
    override val isEmpty = false
    override def map[B](transformer: Transformer[A, B]): MyList[B] =
      new NonEmpty(transformer.transform(head), tail.map(transformer))

    override def toString = {
      @tailrec
      def concatenateElements(reminder: MyList[A], acc: String): String =
        if (reminder.isEmpty) acc
        else concatenateElements(reminder.tail, s"${acc} ${reminder.head}")

      s"[${concatenateElements(this, "")} ]"
    }

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

  class DoublerList extends Transformer[Int, MyList[Int]] {
    override def transform(x: Int): MyList[Int] =
      new NonEmpty(x, new NonEmpty(x + 1, new Empty))
  }

  val listofInterger: MyList[Int] =
    new Empty + 1 + 2 + 3

  @main def genericsMain =
    println("-" * 50)
    val evenPredicate = new Predicate[Int]:
      override def test(x: Int) = x % 2 == 0

    val listofInterger: MyList[Int] =
      new Empty + 1 + 2 + 3

    val doubledInt = listofInterger.map(new Doubler)
    println(doubledInt)

    println("-" * 50)
}
