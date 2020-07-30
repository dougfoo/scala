package forcomp

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3

object Anagrams extends AnagramsInterface {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /** `Occurrences` is a `List` of pairs of characters and positive integers saying
   *  how often the character appears.
   *  This list is sorted alphabetically w.r.t. to the character in each pair.
   *  All characters in the occurrence list are lowercase.
   *
   *  Any list of pairs of lowercase characters and their frequency which is not sorted
   *  is **not** an occurrence list.
   *
   *  Note: If the frequency of some character is zero, then that character should not be
   *  in the list.
   */
  type Occurrences = List[(Char, Int)]

  /** The dictionary is simply a sequence of words.
   *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  val dictionary: List[Word] = Dictionary.loadDictionary

  /** Converts the word into its character occurrence list.
   *
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   *
   *  Note: you must use `groupBy` to implement this method!
   */
  def wordOccurrences(w: Word): Occurrences = w.toLowerCase.toList.groupBy(identity).map(p=> (p._1, p._2.size)).toList.sorted

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s.mkString)

  /** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   *  the words that have that occurrence count.
   *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *
   *  For example, the word "eat" has the following character occurrence list:
   *
   *     `List(('a', 1), ('e', 1), ('t', 1))`
   *
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
   *
   */
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] =
    dictionary.map(w=> (wordOccurrences(w), w)).groupBy(_._1).map(p=>(p._1, p._2.map(x=>x._2)))   // bit ugly...

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences.getOrElse(wordOccurrences(word), List[Word]())

  /** Returns the list of all subsets of the occurrence list.
   *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   *  is a subset of `List(('k', 1), ('o', 1))`.
   *  It also include the empty subset `List()`.
   *
   *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   *    List(
   *      List(),
   *      List(('a', 1)),
   *      List(('a', 2)),
   *      List(('b', 1)),
   *      List(('a', 1), ('b', 1)),
   *      List(('a', 2), ('b', 1)),
   *      List(('b', 2)),
   *      List(('a', 1), ('b', 2)),
   *      List(('a', 2), ('b', 2))
   *    )
   *
   *  Note that the order of the occurrence list subsets does not matter -- the subsets
   *  in the example above could have been displayed in some other order.
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    /*
    Yes my ugly implementation... this is what I found after, very elegant...:
    	occurrences.foldRight(List[Occurrences](Nil))((occ, acc) => acc ++ (for {
    			comb <- acc
		    	i <- 1 to occ._2
		  } yield (occ._1, i) :: comb))
   */
    if (occurrences == Nil || occurrences.isEmpty) List[Occurrences](Nil)
    else {
      // build out a3 -> a3,a2,a1,a0
      def expand(a: (Char,Int)): List[(Char,Int)] = ((1 to a._2) map (b => (a._1, b))).toList

      // for reduceLeft take n-lists and recurse right (L1*L2)*L3 generating tuple combinations
      // L1=List((),(A,1)(A,2) and L2=List((),(B,1)) =>
      //       (List(),List((A,1)),List((A,2)),List((B,1)),
      //        List((A,1),(B,1)), List((A,2),(B,1)), List((A,2),(B,2)), List((A,1),(B,2)))
      val xprod: (List[Any], List[Any]) => List[Any] = (left, right) => for (l <- left; r <- right) yield (l,r)

      // flatten heirarchies to one flat List level
      //    List((('A',1),('B',2)),('C',1)) -> List(('A',1),('B',2),('C',1))
      def flattuple(list: List[(Any)]): List[Any] = {
        def iter(list: List[(Any)], acc: List[(Char, Int)]): List[Any] = {
          if (list.isEmpty) acc
          else {
            list.head match {
              case (a:Char, b:Int) => (a,b) :: acc
              case (a,b) => iter(List(a), acc) ::: iter(List(b), acc)
              case (_) => acc   // actually should map to ()() empty ?
            }}}
        iter(list, List[(Char,Int)]())
      }

      val exp = occurrences.map(v => () :: expand(v))
      val redleft = exp reduceLeft xprod
      val listed = redleft.map(m => List(m))
      val flattup = listed.map(flattuple)
      flattup match { case obj: List[Occurrences] => obj }         //      flattup.asInstanceOf[List[Occurrences]]
    }
  }

  /** Subtracts occurrence list `y` from occurrence list `x`.
   *
   *  The precondition is that the occurrence list `y` is a subset of
   *  the occurrence list `x` -- any character appearing in `y` must
   *  appear in `x`, and its frequency in `y` must be smaller or equal
   *  than its frequency in `x`.
   *
   *  Note: the resulting value is an occurrence - meaning it is sorted
   *  and has no zero-entries.
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    val x2 = x.filter(p => ! y.exists(q => p == q ))        // remove full matches
    x2.map(b => (b._1, b._2 - y.toMap.getOrElse(b._1, 0)))  // sub partial
  }

  /** Returns a list of all anagram sentences of the given sentence.
   *
   *  An anagram of a sentence is formed by taking the occurrences of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *
   *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   *    List(
   *      List(en, as, my),
   *      List(en, my, as),
   *      List(man, yes),
   *      List(men, say),
   *      List(as, en, my),
   *      List(as, my, en),
   *      List(sane, my),
   *      List(Sean, my),
   *      List(my, en, as),
   *      List(my, as, en),
   *      List(my, sane),
   *      List(my, Sean),
   *      List(say, men),
   *      List(yes, man)
   *    )
   *
   *  The different sentences do not have to be output in the order shown above - any order is fine as long as
   *  all the anagrams are there. Every returned word has to exist in the dictionary.
   *
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {   // had to peek at a better solution give creds
    // https://github.com/gnavalesi/forcomp/blob/master/src/main/scala/forcomp/Anagrams.scala
    // for a really elegant one
    def anagrams(occs: Occurrences): List[Sentence] = {
      if (occs.isEmpty) List(Nil)
      else for {
        c <- combinations(occs)
        w <- dictionaryByOccurrences getOrElse(c, Nil)
        s <- anagrams(subtract(occs, wordOccurrences(w)))
        if c.nonEmpty
      } yield w :: s
    }
    anagrams(sentenceOccurrences(sentence))
  }

  //  def main(args: Array[String]) {
  //    println(sentenceAnagrams (List ("Linux", "rulez") ))
  //    println(sentenceAnagrams (List ("sa","ne"))) // -> should do 3 ? (Sean, Sane, as en)
  //  }
}

object Dictionary {
  def loadDictionary: List[String] = {
    val wordstream = Option {
      getClass.getResourceAsStream(List("forcomp", "linuxwords.txt").mkString("/", "/", ""))
    } getOrElse {
      sys.error("Could not load word list, dictionary file not found")
    }
    try {
      val s = scala.io.Source.fromInputStream(wordstream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      wordstream.close()
    }
  }
}
