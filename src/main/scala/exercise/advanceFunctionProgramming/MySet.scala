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

}

class EmptySet[A] extends MySet[A] {

  def contains(elem: A): Boolean = false

  def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)

  def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  def map[B](f: A => B): MySet[B] = new EmptySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  def filter(predicate: A => Boolean): MySet[A] = this

  def forEach(f: A => Unit): Unit = ()

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

  val s = MySet(1,2,3,4)

  s + 4 + 5  ++ MySet(6,7,8) + 9 flatMap (x => MySet(x, 12 * x)) forEach println


}
