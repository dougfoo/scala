import objsets._
import TweetReader._

//def toTweetSet2(l: List[Tweet]): TweetSet = {
//    l.foldLeft(new Empty: TweetSet)(_.incl(_))
//}

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
ts2 union ts2


(ts3 union ts2).filter((x:Tweet) => (x.text.contains("android")))
val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

ts3.filter((x:Tweet) => google.exists(x.text.contains(_)))

var gizmodoTweets = ParseTweets.getTweetData("gizmodo", TweetData.gizmodo)
var gizmodoTweetsSet2 = toTweetSet(gizmodoTweets)

gizmodoTweets.filter((x:Tweet) => (x.text.contains("Apple")))
gizmodoTweetsSet2.filter((x:Tweet) => (x.text.contains("Apple")))

gizmodoTweets.length
var l2 = gizmodoTweets.drop(10)
l2.toList.length

var l3 = toTweetSet(l2) union ts3
l3.toList.length

print("end")



