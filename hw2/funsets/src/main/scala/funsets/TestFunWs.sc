
println("fooey")

type FunSet = Int => Boolean

def contains(s: FunSet, elem: Int): Boolean = s(elem)

/**
 * Returns the set of the one given element.
 */
def singletonSet(elem: Int): FunSet = (x:Int) => x == elem

/**
 * Returns the union of the two given sets,
 * the sets of all elements that are in either `s` or `t`.
 */
def union(s: FunSet, t: FunSet): FunSet = (x:Int) => s(x) || t(x)   // if either

/**
 * Returns the intersection of the two given sets,
 * the set of all elements that are both in `s` and `t`.
 */
def intersect(s: FunSet, t: FunSet): FunSet = (x:Int) => s(x) && t(x)  // if both

/**
 * Returns the difference of the two given sets,
 * the set of all elements of `s` that are not in `t`.
 */
def diff(s: FunSet, t: FunSet): FunSet = (x:Int) =>  s(x) && ! t(x)

/**
 * Returns the subset of `s` for which `p` holds.
 */
def filter(s: FunSet, p: Int => Boolean): FunSet = (x:Int) => s(x) && s(x) == p(x)

/**
 * The bounds for `forall` and `exists` are +/- 1000.
 */
val bound = 1000

/**
 * Returns whether all bounded integers within `s` satisfy `p`.
 */
def forall(s: FunSet, p: Int => Boolean): Boolean = {
  def iter(a: Int): Boolean = {
    if (???) ???
    else if (???) ???
    else iter(???)
  }
  iter(???)
}

/**
 * Returns whether there exists a bounded integer within `s`
 * that satisfies `p`.
 */
def exists(s: FunSet, p: Int => Boolean): Boolean = ???

/**
 * Returns a set transformed by applying `f` to each element of `s`.
 */
def map(s: FunSet, f: Int => Int): FunSet = ???

/**
 * Displays the contents of a set
 */
def toMyString(s: FunSet): String = {
  val xs = for (i <- -bound to bound if contains(s, i)) yield i
  xs.mkString("{", ",", "}")
}

/**
 * Prints the contents of a set on the console.
 */
def printSet(s: FunSet): Unit = {
  println(toMyString(s))
}

var a = singletonSet(1)
var b = singletonSet(2)
var c = singletonSet(3)

printSet(a)
printSet(b)
var d = union(a,b)

printSet(d)
printSet(union(c,d))
printSet(union(a,d))

printSet(intersect(a,c)) // none
printSet(intersect(a,d)) // 1
printSet(intersect(c,d)) // none

printSet(diff(a,b)) // 1
printSet(diff(a,d)) // none
printSet(diff(d,a)) // 2
printSet(diff(d,c)) // 1,2


def evens(x:Int) : Boolean = x % 2 == 0

printSet(filter(d, evens)) // evens
