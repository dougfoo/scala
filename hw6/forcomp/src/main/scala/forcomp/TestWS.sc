

val xs = Map(1->"a",2->"b",3->"cd")
val ys = Map(1->2, 3->4, 5->5)
val zs = Map(1->2, 3->'c', 4->"foo")

for (x <- xs if x._1 > 1) yield x._2

(for (x <- xs if x._1 > 1) yield x._2) mkString ","

// combine 2 lists
val al = List (1,2,3,4)
val bl = List (5,6,7)

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


