// count # of ways to make
def countWays(money: Int, coins: List[Int]): Int = {
  println(s"countWays: $money $coins")
  var ways = 0
  for (coin <- coins)
    if (coin <= money)
      ways += countWays(money-coin, coins) + 1
      println(s"ways: $ways ")
  ways
}
countWays(5,List(1))
countWays(5,List(1,2))


def countChange(money: Int, coins: List[Int]): Int = {
  println("countChange")

  /* eg money=f(5), coins=[5,2,1] -- fix coins
     recurse deep and get
       f(5) = f(4) + x  [1111]+1,[211]+1,[22]+1,5
       f(4) = f(3) + x  [111]+1,[21]+1,[2]+2 (??) -> 1-1-1-1,2-1-1,2-2
       f(3) = f(2) + x  [11]+1,[2]+1 -> 1-1-1,2-1
       f(2) = f(1) + x  [1]+1,2 -> 1-1,2
       f(1) = f(0) + 1  [1]
       f(0) = 0
  */

  def countPart(money: Int, coins: List[Int], count: Int): Int = {
    if (coins.length == 0)
      0
    else
      countPart(money-1, coins, count + countWays(money, coins))
  }

  countPart(money, coins.sorted(Ordering.Int.reverse), 0)
}


//
//countChange(0,List(1))       //
//countChange(1,List(1))       // 1
//countChange(2,List(1))       // 11
//countChange(4,List(1,2))     // 1111, 112, 22 = 3
//countChange(6,List(1,2,5))   // 111111 11112 1122 222 15
//countChange(10,List(1,2,5))  // 1^10 .....  55


/*

6 w/ 1,2,5's

           6
       1  2   5
     1   1  2  1
    1   1  1 2
   1   1  1
  1   1
 1

 */


/*
import recfun.RecFun.pascal

//def pascal(c: Int, r: Int): Int = {
//  if (c < 0 || c > r)
//    0
//  else if (r==0)
//    1
//  else
//    pascal(c-1,r-1) + pascal(c, r-1)
//}
//
//pascal(0,0)
//pascal(0,2)
//pascal(1,2)
//pascal(1,3)
//pascal(0,10)
//pascal(1,10)
//pascal(5,10)
//pascal(3,15)
//pascal(3,18)
def balance(chars: List[Char]): Boolean = {
  def trim(chars: List[Char], count: Integer): Boolean = {
    if (chars.length == 0)
      count == 0
    else
      if (chars.head == '(')
        trim(chars.drop(1), count+1)
      else if (chars.head == ')')
        trim(chars.drop(1), count-1)
      else
        trim(chars.drop(1), count)
  }
  trim(chars, 0)
}
println("Hello")
balance("ABCDEF".toList)
balance("".toList)
balance("()".toList)
balance("(()".toList)
balance("())".toList)
balance("AB(CDEF)".toList)
balance("AB()CDE()F".toList)
balance("AB( )CDE(F".toList)
balance("AB(A )CDE(F".toList)
balance("AB( ))CDE(F)".toList)
balance("AB( CDE(F)dfd) ".toList)

var f = "Doug Cha".toList
*/


