package part2

import java.util.Random
import scala.util.Try

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

  @main def tryes =
    println("from tries")

    val program: Try[String] =
      for
        service <- Try(HttpService.getConnection(host, port))
        html <- Try(service.get(url))
      yield html

    val collectResults = program.fold(e => e, s => s)

    // println(program.getOrElse("Failled service"))
    println(collectResults)
