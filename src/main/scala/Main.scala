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

  class Crocodile extends Animal:
    override val creatureType = "very wild"
    override def eat(): Unit = println("I can eat anything, I'm a Croc")
    def eat(animal: Animal): Unit = println(s"eating this pour")
    def eat(dog: Dog): Unit = println("Eating dog")
    def eat(person: Person): Unit = println(s"Eating ${person.name}")
    // def eat(person: Person): Int = 45
    def eat(person: Person, animal: Animal): Int = 45
// def eat(person: Person, animal: Animal): Unit = println("It works ")
