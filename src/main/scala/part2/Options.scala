package part2

import java.util.Random

object Options:
  val config = Map(
    "local" -> "1234.1234.1234",
    "port" -> "9999"
  )

  class Connect:
    def connection(): String = "successful"
  object Connect:
    val random = new Random
    def apply(host: String, port: String): Option[Connect] =
      if (random.nextBoolean()) Some(new Connect)
      else None

  @main def OptionsMain =
    println("from Options Main")
    val local: Option[String] = config.get("local")
    val port: Option[String] = config.get("port")
    val connecting: Option[String] =
      for
        l <- local
        p <- port
        singleton <- Connect(l, p)
      yield singleton.connection()
    println(connecting.getOrElse("no stablished connection"))
