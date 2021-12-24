package part2

abstract class Maybe[A]:
  def map[B](f: A => B): Maybe[B]
  def filter(predicate: A => Boolean): Maybe[A]
  def flatMap[B](f: A => Maybe[B]): Maybe[B]
