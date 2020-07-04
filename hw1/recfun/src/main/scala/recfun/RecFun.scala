package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0 || c > r)
      0
    else if (r==0)
      1
    else
      pascal(c-1,r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
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

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def countWays(money: Int, coins: List[Int]): Int = {
//      println(s"countWays: $money $coins")
      if (coins.isEmpty) 0
      else if (money == 0) 1
      else if (money == coins.head) {
        countChange(money, coins.drop(1)) + 1
      }
      else if (money < coins.head) {
        countChange(money, coins.drop(1))
      }
      else {
        countChange(money, coins)
      }
    }

//    println(s"countChange $money coins $coins")
    if (coins.isEmpty) 0
    else
      countWays(money-coins.head, coins) + countChange(money, coins.drop(1))
  }
}
