abstract class LList:
  def head: Int
  def tail: LList
  def isEmpty: Boolean
  def add(element: Int): LList = new Cons(element, this)
  override def toString = super.toString

class Empty extends LList {
  def head: Int = throw new NoSuchElementException
  def tail: LList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  override def add(element: Int): LList = new Cons(element, new Empty)
  override def toString = "[]"
}

class Cons(override val head: Int, override val tail: LList) extends LList {
  def isEmpty: Boolean = false
  override def toString = {
    def concatenateElements(reminder: LList, acc: String): String =
      if (reminder.isEmpty) acc
      else concatenateElements(reminder.tail, s"${acc} ${reminder.head}")

    concatenateElements(this, "")
  }
}

object LListTest:
  def main(args: Array[String]): Unit =
    println("Hello from here ")
