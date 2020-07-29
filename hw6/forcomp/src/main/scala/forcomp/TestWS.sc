// val occurrences = List(('a', 2), ('b', 2), ('c',2))
val occurrences = List(('a', 2), ('b', 2), ('c', 3), ('d',1))
def expand(a: (Char,Int)): List[(Char,Int)] = ((1 to a._2) map (b => (a._1, b))).toList
val xprod: (List[Any], List[Any]) => List[Any] = (left, right) =>
  for {
    l <- left; r <- right
  } yield (l,r)
// merge to same level
//    List((('A',1),('B',2)),('C',1)) -> List(('A',1),('B',2),('C',1))
def flattuple(list: List[(Any)]): List[Any] = {
  def iter(list: List[(Any)], acc: List[(Char, Int)]): List[Any] = {
    if (list.isEmpty) acc
    else {
      list.head match {
        case (a:Char, b:Int) => { //println(s"char,int tuple $a $b");
          (a,b) :: acc }
        case (a,b) => { //println(s"tuple $a , $b");
          iter(List(a), acc) ::: iter(List(b), acc) }
        case (_) => acc   // actually should map to ()() empty ?
      }}}
  iter(list, List[(Char,Int)]())
}

val exp = occurrences.map(v => ()::expand(v))
val redleft = exp reduceLeft xprod
val listed = redleft.map(m=>List(m))  // fill empty ()
val flattup = listed.map(flattuple)

o3




def tcast(m:Any): Tuple2[Any,Any] = {
  m match {
    case tuple @ (a: Any, b: Any) => Tuple2(a,b)
  }
}


break

//def xprod(l: List[(Char,Int)]): List[(Char,Int)] = {
//  l.foldLeft((x =>
//}
//  (for (k <- l)
//    for (j <- k)
//      yield (j))
//}


(for {
  (k, v) <- occurrences
  c <- (1 to v)
} yield (k, c)).groupBy(_._1)

((1 until 5) map (i =>
  (1 until 4) map (j => (i, j)))).flatten

for ((k,v) <- occurrences)

// combine 2 lists
val al = List (1,2,3,4)
val bl = List (5,6,7)

for {
  a <- al
  b <- bl
} println(s"$a + $b")


val xs = Map(1->"a",2->"b",3->"cd")
val ys = Map(1->2, 3->4, 5->5)
val zs = Map(1->2, 3->'c', 4->"foo")

(for ((x,y) <- xs zip ys)
  yield x*y).foldLeft(0.0)(_ + _)

def scalar(xs: List[Double], ys: List[Double]): Double = {
  (for ((x,y) <- xs zip ys)
    yield x*y).foldLeft(0.0)(_ + _)
}

scalar(List(1.0, 2.0), List(3, 4))


for (x <- xs if x._1 > 1) yield x._2

(for (x <- xs if x._1 > 1) yield x._2) mkString ","

// combine 2 lists
val al = List (1,2,3,4)
val bl = List (5,6,7,8)

for {
  a <- al
  b <- bl
  if (a+b < 8)
} println(s"$a + $b")

val donuts = List("Vanilla","Vanilla","Pink","Pink","Pumpkin","Pink","Blue")
donuts.map(x => (x, donuts.count(_ == x)))

donuts.groupBy(identity).map(x=> (x._1, x._2.size))

((1 until 5) map (i =>
  (1 until 4) map (j => (i, j)))).flatten

((1 until 5) flatMap (i =>
  (1 until 4) map (j => (i, j))) filter (p => p._1 == 1) )


val cl = (1 until 5)
val dl = (1 until 4)
for {
  c <- cl
  d <- dl
} yield (c,d)

for {
  c <- cl
  d <- dl
  if (c == 1)
} yield (c,d)

(donuts filter (p => p.charAt(0) != 'B')).groupBy(identity).
  map(p=> (p._1, p._2.size))("Pink")


