import objsets._

var t1 = new Tweet("doug","my tweet",1)
var t2 = new Tweet("jacky","tweet 2",2)
var t3 = new Tweet("doug","android sucks",333)
var t4 = new Tweet("david","apple is great",111)

var ts1 = new NonEmpty(t1, new Empty, new Empty)
var ts2 = new NonEmpty(t2, new Empty, new Empty)
var ts3 = new NonEmpty(t3, new NonEmpty(t4, new Empty(), new Empty()), new Empty)
var tsall = ts3 union ts1 union ts2

var c1 = new Cons(t1, Nil)

tsall
ts1.mostRetweeted
println("descending")
ts1.descendingByRetweet  // 1 elem

//ts3.mostRetweeted
//ts3.descendingByRetweet  // 2 elem
//
//tsall.mostRetweeted
//tsall.descendingByRetweet  // 4 elem


println("done")
