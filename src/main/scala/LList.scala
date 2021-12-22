import scala.annotation.tailrec
abstract class LList:
  def head: Int
  def tail: LList
  def isEmpty: Boolean
  infix def +(element: Int): LList = new Cons(element, this)
  override def toString = super.toString

class Empty extends LList {
  def head: Int = throw new NoSuchElementException
  def tail: LList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  override infix def +(element: Int): LList = new Cons(element, new Empty)
  override def toString = "[]"
}

class Cons(override val head: Int, override val tail: LList) extends LList {
  def isEmpty: Boolean = false
  override def toString = {
    @tailrec
    def concatenateElements(reminder: LList, acc: String): String =
      if (reminder.isEmpty) acc
      else concatenateElements(reminder.tail, s"${acc} ${reminder.head}")

    s"[${concatenateElements(this, "")}]"
  }
}

object LListTest:
  def main(args: Array[String]): Unit =
    println("Hello from here ")
    val empty = new Empty
    val first3numbers = new Cons(1, new Cons(2, new Cons(3, empty)))
    println(first3numbers)
    val first2 = empty + 1 + 2 + 3
