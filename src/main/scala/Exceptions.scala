object Exceptions:
  val aWierdValue: Int = throw new java.lang.NullPointerException

  def getInt(withExpections: Boolean): Int =
    if (withExpections) throw new RuntimeException("No Int for you")
    else 42

  val potentialfail: Int =
    try getInt(true)
    catch case e: RuntimeException => 54

end Exceptions

@main def ExceptionsMain =
  println("Hello from Exceptions")
