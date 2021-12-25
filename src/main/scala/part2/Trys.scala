package part2

import java.util.Random

object Trys:
  val port = "8080"
  val host = "local"
  val url = "url"

  class Connection:
    val random = new Random
    def get(url: String): String =
      if (random.nextBoolean()) "html success"
      else throw new RuntimeException("sorry")

  object HttpService:
    val random = new Random
    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("service down")
