@main def hello: Unit =
  class Animal:
    val creatureType = "wild"
    def eat(): Unit = println("nomonm")

  class Cat extends Animal:
    def crounch() =
      eat()
      println("Crounch, Crounch")

  val cat = new Cat

  class Person(val name: String, age: Int):
    def this(name: String) = this(name, 0)

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  class Dog extends Animal:
    override val creatureType = "domestic"
    override def eat(): Unit = super.eat()
    override def toString: String = "a dog"

  //subtype poliphormism
  val dog: Animal = new Dog
  dog.eat()
