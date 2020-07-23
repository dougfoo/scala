package patmat

import org.junit._
import org.junit.Assert.assertEquals

class HuffmanSuite {
  import Huffman._

  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }


  @Test def `weight of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(5, weight(t1))
    }


  @Test def `chars of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(List('a','b','d'), chars(t2))
    }

  @Test def `string2chars hello world`: Unit =
    assertEquals(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'), string2Chars("hello, world"))


  @Test def `make ordered leaf list for some frequency table (15pts)`: Unit =
    assertEquals(List(Leaf('e',1), Leaf('t',2), Leaf('x',3)), makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))))


  @Test def `combine of some leaf list (15pts)`: Unit = {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)), combine(leaflist))
  }

  @Test def `until test`: Unit = {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(List(Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3),Leaf('x',4),List('e', 't', 'x'),7)), until(singleton, combine)(leaflist))
  }

  @Test def `createCodeTree test`: Unit = {
    assertEquals(Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3),Leaf('x',4),List('e', 't', 'x'),7), createCodeTree(string2Chars("xetxtxx")))
  }

  @Test def `decode test why are bits reversed order ?? 1/0 flip?`: Unit =
    new TestTrees {
      val t = createCodeTree(string2Chars("xetxtxx"))
      println(t)
      assertEquals("x".toList, decode(t, List(1)))
      assertEquals("xx".toList, decode(createCodeTree(string2Chars("xetxtxx")), List(1,1)))
      assertEquals("xtx".toList, decode(createCodeTree(string2Chars("xetxtxx")), List(1,0,1,1)))
      assertEquals("huffmanestcool".toList, decode(frenchCode, secret))
    }

  @Test def `encode test`: Unit =
    new TestTrees {
      println(t1)
      assertEquals(List(0,1), encode(t1)("ab".toList))
      assertEquals(List(0,0), encode(t1)("aa".toList))
      assertEquals(List(1,1), encode(t1)("bb".toList))
      println(t2)
      assertEquals(List(0,1,0,0,1), encode(t2)("bad".toList))
    }


  @Test def `decode and encode a very short text should be identity (10pts)`: Unit =
    new TestTrees {
      assertEquals("ab".toList, decode(t1, encode(t1)("ab".toList)))
      assertEquals("abd".toList, decode(t2, encode(t2)("abd".toList)))
    }

  @Test def `codebits test`: Unit =
    new TestTrees {
      val cb = List(('a',List(0)),('b',List(1)))
      assertEquals(List(0), codeBits(cb)('a'))
      assertEquals(List(1), codeBits(cb)('b'))
      val cb2 = List(('a',List(0)),('b',List(1,0)), ('c',List(1,1)))
      assertEquals(List(0), codeBits(cb2)('a'))
      assertEquals(List(1,0), codeBits(cb2)('b'))
      assertEquals(List(1,1), codeBits(cb2)('c'))
    }


  @Test def `codetable test`: Unit =
    new TestTrees {
      val a=('a',List[Bit](0))::List[(Char, List[Bit])]()
      val b=('b',List[Bit](1,0))::List[(Char, List[Bit])]()
      val c=('c',List[Bit](1,1))::List[(Char, List[Bit])]()

      assertEquals(List(('a',List(0)), ('b',List(1, 0))), mergeCodeTables(a,b))
      assertEquals(List(('b',List(1,0)), ('c',List(1, 1))), mergeCodeTables(b,c))
    }

  @Test def `quickencode test`: Unit =
    new TestTrees {
      assertEquals(List(0,1,0,0,1), quickEncode(t2)("bad".toList))
      assertEquals(List(1,1), quickEncode(t1)("bb".toList))
    }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
