package part2

abstract class Maybe[A]:
  def map[B](f: A => B): Maybe[B]
  def filter(predicate: A => Boolean): Maybe[A]
  def flatMap[B](f: A => Maybe[B]): Maybe[B]

class MaybeNot[A] extends Maybe[A]:
  override def map[B](f: A => B): Maybe[B] = MaybeNot[B]()
  override def filter(predicate: A => Boolean): Maybe[A] = this
  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = MaybeNot[B]()

case class Just[A](value: A) extends Maybe[A]:
  override def map[B](f: A => B): Maybe[B] = Just(f(value))
  override def filter(predicate: A => Boolean): Maybe[A] =
    if (predicate(value)) this
    else MaybeNot[A]()

  override def flatMap[B](f: A => Maybe[B]): Maybe[B] =
    f(value)

@main def MaybeMain =
  val maybeTest = Just(3)
  val mapMaybe = maybeTest.map(_ + 3)
  println(mapMaybe)
