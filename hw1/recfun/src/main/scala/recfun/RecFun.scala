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
        else if (chars.head == ')')
          trim(chars.drop(1), count-1)
        else
          trim(chars.drop(1), count)
    }
    trim(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = 5

}
