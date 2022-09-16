package exercise.advanceScala

import scala.annotation.tailrec

object AdvancedPatternMatching extends App {

  val numbers = List(-121,1,2,3,4,5,6)
  val description = numbers match {
    case head :: Nil => println(s"the element is $head.")
    case _ => println("There is Nothing.")
  }

  class Person(val name: String, val age: Int)

  object PersonMatching {

    def unapply(person: Person): Option[(String, Int)] = {
      if (person.age < 17) {
        throw new MatchError("Sorry Something went Wrong!!!!")
      }
      else Some((person.name, person.age))
    }

    def unapply(age: Int): Option[(String)] = {
      Some(if (age < 18) "Younger" else "Older")
    }

    def apply(person: Person): Option[(String, Int)] = Some((person.name, person.age))
  }

  val harshang = PersonMatching.apply(new Person("Harshang", 18))
  val parth = new Person("Pcube", 24)
  val greeting = harshang match {
    case Some(a, b) => s"Hello my name is $a and i am $b years old."
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

  //   infix patterns

  case class OrClass[A,B](a: A, b:B)
  val either = OrClass(2, "TWO")

  val descrptions = either match {
    case a OrClass b => s"$a is written as $b."
  }

  println(descrptions)

  val anotherArgument = numbers match {
    case List(1,_*) => "Starting with 1"
    case _ => "Sorry Nothing Results to show"
  }

  println(anotherArgument)

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A] ) extends MyList[A]

  object TheList {

    def  unapplySeq[A](list: MyList[A]): Option[Seq[A]] = {
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
    }
  }

  val myList : MyList[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))

  val actualList = myList match {
    case TheList(1,2,_*) => "Value is starting from 1,2"
  }

  println(actualList)

//  custom return type for unapply
//  isEmpty: Boolean, get: something

  abstract class Wrapper[T] {

    def isEmpty: Boolean

    def get: T
  }

    object PersonWrapper {
      def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
        def isEmpty = false
        def get: String = person.name
      }
    }

    println(parth match {
      case PersonWrapper(sName) => s"This Person`s name is $sName and ${parth.name}"
      case _ => "An Alien"
    })
}
