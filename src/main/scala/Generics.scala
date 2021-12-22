object Generics {
  abstract class MyList[T]:
    def head: T
    def tail: MyList[T]

  class Empty[T] extends MyList[T] {
    override def head: T = throw new NoSuchElementException
    override def tail: MyList[T] = throw new NoSuchElementException

    class NonEmpty[T](override val head: T, override val tail: MyList[T])
        extends MyList[T]
  }
}
