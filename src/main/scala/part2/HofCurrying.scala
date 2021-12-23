package part2

import scala.annotation.tailrec

object HofCurrying:

  abstract class LList[A]:
    def head: A
    def tail: LList[A]
    val isEmpty: Boolean
    infix def +(element: A): LList[A] = Cons(element, this)
    infix def ++(anotherList: LList[A]): LList[A]
    def map[B](transformer: A => B): LList[B]
    def filter(predicate: A => Boolean): LList[A]
    def flatMap[B](transformer: A => LList[B]): LList[B]
    def foreach(tranformer: A => Unit): Unit
    def sort(comparator: (A, A) => Int): LList[A]
    def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B]
    def foldLeft[B](start: B)(operator: (B, A) => B): B

  case class Empty[A]() extends LList[A]:
    override def head: A = throw new NoSuchElementException
    override def tail: LList[A] = throw new NoSuchElementException
    override val isEmpty: Boolean = true
    override def map[B](transformer: A => B): LList[B] = Empty()
    override def filter(predicate: A => Boolean): LList[A] = this
    override def ++(anotherList: LList[A]): LList[A] = anotherList
    override def flatMap[B](transformer: A => LList[B]): LList[B] = Empty()
    override def foreach(tranformer: A => Unit): Unit = ()
    override def sort(comparator: (A, A) => Int): LList[A] = this
    override def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B] =
      Empty()
    override def foldLeft[B](start: B)(operator: (B, A) => B): B = start

  case class Cons[A](override val head: A, override val tail: LList[A])
      extends LList[A]:
    override val isEmpty = false
    override def map[B](transformer: A => B): LList[B] =
      Cons(transformer(head), tail.map(transformer))
    override def filter(predicate: A => Boolean): LList[A] =
      if (predicate(head)) Cons(head, tail.filter(predicate))
      else tail.filter(predicate)
    override def ++(anotherList: LList[A]): LList[A] =
      Cons(head, tail ++ anotherList)
    override def flatMap[B](transformer: A => LList[B]): LList[B] =
      transformer(head) ++ tail.flatMap(transformer)
    override def toString =
      @tailrec
      def concatenateElements(reminder: LList[A], acc: String): String =
        if (reminder.isEmpty) acc
        else concatenateElements(reminder.tail, s"${acc} ${reminder.head}")
      s"[${concatenateElements(this, "")} ]"
    def foreach(tranformer: A => Unit): Unit =
      tranformer(head)
      tail.foreach(tranformer)
    override def sort(comparator: (A, A) => Int): LList[A] =
      def insert(element: A, sortedList: LList[A]): LList[A] =
        if (sortedList.isEmpty) Cons(element, Empty())
        else if (comparator(element, sortedList.head) <= 0)
          Cons(element, sortedList)
        else Cons(sortedList.head, insert(element, sortedList.tail))
      val sortedTail = tail.sort(comparator)
      insert(head, sortedTail)
    override def zipWith[B, T](list: LList[T], zip: (A, T) => B) =
      if (list.isEmpty)
        throw new IllegalArgumentException("Zipping list of non equal lengths")
      else Cons(zip(head, list.head), tail.zipWith(list.tail, zip))
    override def foldLeft[B](start: B)(operator: (B, A) => B): B =
      tail.foldLeft(operator(start, head))(operator)

  @main def Hof =
    val listofInterger: LList[Int] =
      Empty() + 8 + 9 + 5 + 3 + 1 + 2

    // listofInterger.foreach(x => println(x))

    val sortedList = listofInterger.sort((x, y) => x - y)
    println(sortedList)

    val secondListofInteger: LList[Int] =
      Empty() + 8 + 4 + 5 + 6 + 3 + 6

    val testZipWith =
      listofInterger.zipWith(sortedList, (x, y) => x + y)

    println(testZipWith)

    val foldLeftTest = sortedList.foldLeft(0)((x, y) => x + y)
    println(foldLeftTest)

end HofCurrying
