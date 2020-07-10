import objsets._

var t1 = new Tweet("doug","my tweet",1)
var t2 = new Tweet("jacky","tweet 2",2)
var t3 = new Tweet("doug","android sucks",33)
var t4 = new Tweet("david","apple is great",111)

var ts1 = new NonEmpty(t1, new Empty, new Empty)
var ts2 = new NonEmpty(t2, new Empty, new Empty)
var ts3 = new NonEmpty(t3, new NonEmpty(t4, new Empty(), new Empty()), new Empty)

ts1 union ts2
ts2 union ts1
ts3 union ts2

(ts3 union ts2).filter((x:Tweet) => (x.text.contains("android")))
val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

ts3.filter((x:Tweet) => google.exists(x.text.contains(_)))


