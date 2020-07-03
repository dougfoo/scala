
def countKids(money: Int, coins: List[Int], cc: (Int, List[Int], List[Int]) => Int, chain: List[Int] = List()): Int = {
  println(s"countKids $money coins $coins")
  var ct = 0     // agg count
  var last = 0   // last coin
  var cur = 0    // cur coint
  for (coin <- coins)  // prob can make flat func ?
    cur = coin
    ct += cc(money-cur, coins.drop(last), chain)  // need to drop coin on next iteration...
    last = cur
  ct
}

def countPaths(money: Int, coins: List[Int], cc: (Int, List[Int], List[Int]) => Int, chain: List[Int] = List()): Int = {
  println(s"countPaths $money coins $coins")
  if (coins.isEmpty) 0
  else
    cc(money-coins.head, coins, chain) + countPaths(money, coins.drop(1), cc, chain)
}

def countChange(money: Int, coins: List[Int], chain: List[Int]=List()): Int = {
  println(s"countChange: $money $coins")
  if (coins.isEmpty) 0
  else if (money == coins.head) {
    var c = money :: chain
    println(s"--adding 1, chain: $c")
    countPaths(money, coins.drop(1), countChange, money :: chain) + 1
  }
  else if (money < coins.head) {
    var c = coins.head
    countPaths(money, coins.drop(1), countChange, c :: chain)
  }
  else {
    countPaths(money, coins, countChange, chain)
  }
}

assert (3 == countPaths(4,List(2,1), countChange))
//assert (0 == countWays(1,List(2)))
//assert (1 == countWays(3,List(1)))
//assert (1 == countWays(2,List(2)))
//assert (1 == countWays(4,List(2)))
//assert (2 == countWays(2,List(2,1)))
//assert (3 == countWays(5,List(2,1)))

/* eg money=f(5), coins=[5,2,1] -- fix coins
   recurse deep and get
     f(5) = f(4) + x  [1111]+1,[211]+1,[22]+1,5
     f(4) = f(3) + x  [111]+1,[21]+1,[2]+2 (??) -> 1-1-1-1,2-1-1,2-2
     f(3) = f(2) + x  [11]+1,[2]+1 -> 1-1-1,2-1
     f(2) = f(1) + x  [1]+1,2 -> 1-1,2
     f(1) = f(0) + 1  [1]
     f(0) = 0
*/

countChange(4,List(2))

println("Done")

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


