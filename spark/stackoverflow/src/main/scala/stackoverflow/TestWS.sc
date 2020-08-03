

val map = Map(4->(8,8), 1->(1,3), 2->(3,4), 3->(5,6))

map.mapValues(x => x)

map.values

map.values.foldLeft(0)(_ + _._2)
