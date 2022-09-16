package exercise.advanceScala

object AdvancedPatternMatching extends App {

  val numbers = List(121)
  val description = numbers match {
    case head :: Nil => println(s"the element is $head.")
    case _ => println("There is Nothing.")
  }

  class Person(val name: String, val age: Int)

  object PersonMatching {

    def unapply (person: Person): Option[(String,Int)] = {
      if (person.age < 17) {
        throw new MatchError("Sorry Something went Wrong!!!!")
      }
      else Some((person.name, person.age))
    }

    def unapply (age: Int): Option[(String)] = {
      Some(if (age < 18) "Younger" else "Older")
    }

    def apply (person: Person): Option[(String,Int)] = Some((person.name, person.age))
  }

  val harshang = PersonMatching.apply(new Person("Harshang", 18))
  val parth = new Person("Pcube", 24)
  val greeting = harshang match {
    case Some(a,b) => s"Hello my name is $a and i am $b years old."
  }

  val greeting1 = parth match {
    case PersonMatching(a, b) => s"Hello my name is $a and i am $b years old."
  }

  println(greeting)

  println(greeting1)

  val stausOfAge = parth.age match {
    case PersonMatching(a) => s"Hello My name is ${parth.name} and I am $a."
  }

  println(stausOfAge)

}
