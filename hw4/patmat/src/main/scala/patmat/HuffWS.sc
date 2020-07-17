println("start")
abstract class CodeTree

case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
case class Leaf(char: Char, weight: Int) extends CodeTree

def weight(tree: CodeTree): Int = tree match { // tree match ...
  //sum of left/right weight?
  case Leaf(c,w) => w
  case Fork(l,r,cs,w) => weight(l) + weight(r)
}

def chars(tree: CodeTree): List[Char] =  tree match { // tree match ...
  case Leaf(c,w) => c :: List()
  case Fork(l,r,cs,w) =>  chars(l) ::: chars(r)   // and add chars(r) ??
}

var a = Leaf('A', 5)
var b = Leaf('B', 3)
var f = Fork(a,b, chars(a):::chars(b), weight(a)+weight(b))

weight(f)

chars(f)



