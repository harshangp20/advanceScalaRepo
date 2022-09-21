package exercise.advanceFunctionProgramming

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {

  def apply(elem: A): Boolean =
    contains(elem)

  def contains(elem: A): Boolean

  def +(elem: A): MySet[A]

  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def filter(predicate: A => Boolean): MySet[A]

  def forEach(f: A => Unit): Unit

  def -(elem: A): MySet[A]

  def --(anotherSet: MySet[A]): MySet[A] // difference

  def &(anotherSet: MySet[A]): MySet[A] // intersection

  def unary_! : MySet[A]

}

class EmptySet[A] extends MySet[A] {

  def contains(elem: A): Boolean = false

  def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)

  def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  def map[B](f: A => B): MySet[B] = new EmptySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  def filter(predicate: A => Boolean): MySet[A] = this

  def forEach(f: A => Unit): Unit = ()

  //  implementation

  def -(elem: A): MySet[A] = this

  def --(anotherSet: MySet[A]): MySet[A] = this

  def &(anotherSet: MySet[A]): MySet[A] = this

  def unary_! : MySet[A] = new PropertyBasedSet[A](_ => true)

}

 // all elements of tye A which satisfya a property
 // { x in A | property(x) }
class PropertyBasedSet[A] (property : A => Boolean) extends MySet[A] {

   override def contains(elem: A): Boolean = property(elem)

//   { x in A | property(x) } + element = { x in A | property(x) || x == element }
   override  def +(elem: A): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || x == elem)

//   { x in A | property(x) } + element = { x in A | property(x) || set contains x }
   override def ++(anotherSet: MySet[A]): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || anotherSet(x))

//  all integers => (_ % 3) => [0 1 2]

   override def map[B](f: A => B): MySet[B] = politelyFail

   override def flatMap[B](f: A => MySet[B]): MySet[B] = politelyFail

   override def forEach(f: A => Unit): Unit = politelyFail

    override def filter(predicate: A => Boolean): MySet[A] = new PropertyBasedSet[A](x => property(x) && predicate(x))  // property-based set

   override def -(elem: A): MySet[A] =filter(x => x != elem)

   override def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)

   override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)

   override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))

   def politelyFail = throw new IllegalArgumentException("Something Getting Error")
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {

  def contains(elem: A): Boolean =
    elem == head || tail.contains(elem)

  def +(elem: A): MySet[A] =
    if (this.contains(elem)) this
    else new NonEmptySet[A](elem, this)

  def ++(anotherSet: MySet[A]): MySet[A] =
    tail ++ anotherSet + head

  def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  def filter(predicate: A => Boolean): MySet[A] = {

    val filterTail = tail filter predicate

    if (predicate(head)) filterTail + head
    else filterTail

  }

  def forEach(f: A => Unit): Unit = {

    f(head)
    tail forEach f

  }

  def -(elem: A): MySet[A] =
    if (head == elem) tail
    else tail - elem + head

  def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)

  def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)

  def unary_! : MySet[A] = new PropertyBasedSet[A](x => this.contains(x))

}

object MySet {

  def apply[A](value: A*): MySet[A] = {

    @tailrec
    def buildSet(valSeq: Seq[A], acc: MySet[A]): MySet[A] =
      if (valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)

    buildSet(value.toSeq, new EmptySet[A])
  }

}

object MySetVariables extends App {

  val s = MySet(1, 2, 3, 4)

  s + 6 ++ MySet(-1,-2)  forEach println

//  s + 4 + 5 ++ MySet(6, 7, 8) + 9 flatMap (x => MySet(x, 12 * x)) forEach println

  val negativeNumber = !s
  println(negativeNumber(6))
  println(negativeNumber(1))

}
