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

  case class Empty[A]() extends LList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: LList[A] = throw new NoSuchElementException
    override val isEmpty: Boolean = true
    override def map[B](transformer: A => B): LList[B] =
      Empty()
    override def filter(predicate: A => Boolean): LList[A] =
      this

    override def ++(anotherList: LList[A]): LList[A] = anotherList
    override def flatMap[B](transformer: A => LList[B]): LList[B] =
      Empty()

  }

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
