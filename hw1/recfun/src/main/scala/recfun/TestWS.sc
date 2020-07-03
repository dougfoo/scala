import scala.collection.mutable.Stack

def balance(chars: List[Char]): Boolean = {
  def trim(chars: List[Char], count: Integer): Boolean = {
    if (chars.length == 0)
      count == 0
    else
      if (chars.head == '(')
        trim(chars.drop(1), count+1)
      else if (chars.head == ')' && count > 0)
        trim(chars.drop(1), count-1)
      else if (chars.head == ')' && count == 0)
        false
      else
        trim(chars.drop(1), count)
  }
  trim(chars, 0)
}

println("Hello")
assert(true == balance("ABCDEF".toList))
assert(true == balance("".toList))
assert(false == balance("())(".toList))
assert(true == balance("()".toList))
assert(false == balance("(()".toList))
assert(false == balance("())".toList))
assert(false == balance("AB(CDEF)".toList))
assert(true == balance("AB()CDE()F".toList))
assert(false == balance("AB( )CDE(F".toList))
assert(false == balance("AB(A )CDE(F".toList))
assert(false == balance("AB( ))CDE(F)".toList))
assert(true == balance("AB( CDE(F)dfd) ".toList))

var f = "Doug Cha".toList

print(f)
print("all done")

//
//def countChange(money: Int, coins: List[Int]): Int = {
//  def countWays(money: Int, coins: List[Int]): Int = {
//    if (coins.isEmpty) 0
//    else if (money == 0) 1
//    else if (money == coins.head) {
//      countChange(money, coins.drop(1)) + 1
//    }
//    else if (money < coins.head) {
//      countChange(money, coins.drop(1))
//    }
//    else {
//      countChange(money, coins)
//    }
//  }
//
//  if (coins.isEmpty) 0
//  else
//    countWays(money-coins.head, coins) + countChange(money, coins.drop(1))
//}
//
//
//assert (3 == countChange(4,List(2,1)))
//assert (0 == countChange(1,List(2)))
//assert (1 == countChange(3,List(1)))
//assert (1 == countChange(2,List(2)))
//assert (1 == countChange(4,List(2)))
//assert (2 == countChange(2,List(2,1)))
//assert (3 == countChange(5,List(2,1)))
//assert (5 == countChange(5,List(3,2,1)))
//assert (6 == countChange(5,List(5,3,2,1)))
//assert (4 == countChange(5,List(5,2,1)))
//assert (5 == countChange(6,List(5,2,1)))
//assert (1022 == countChange(300,List(5,10,20,50,100,200,500)))
//
//println("done!")
//
///* eg money=f(5), coins=[5,2,1] -- fix coins
//   recurse deep and get
//     f(6) = f(5) + x  [11111]+1,[2111]+1,[221]+1,[5]+1,222
//     f(5) = f(4) + x  [1111]+1,[211]+1,[22]+1,5
//     f(4) = f(3) + x  [111]+1,[21]+1,[2]+2 (??) -> 1-1-1-1,2-1-1,2-2
//     f(3) = f(2) + x  [11]+1,[2]+1 -> 1-1-1,2-1
//     f(2) = f(1) + x  [1]+1,2 -> 1-1,2
//     f(1) = f(0) + 1  [1]
//     f(0) = 0
//*/
//
//
//import recfun.RecFun.pascal
//
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

