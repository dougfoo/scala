

  val dictionary: List[Word] = Dictionary.loadDictionary          // load file w/ valid words

  def wordOccurrences(w: Word): List[(Char, Int)] =               // take "Odor" -> {d:1, o:2, r:1}
     w.toLowerCase.toList.groupBy(identity).map(p=> (p._1, p._2.size)).toList.sorted 

  lazy val dictionaryByOcc: Map[List[(Char, Int)], List[Word]] =  // dict of {d:1, o:2, r:1} -> ["odor","door","ordo","rood"]
     dictionary.map(w=> (wordOccurrences(w), w)).groupBy(_._1).map(p=>(p._1, p._2.map(x=>x._2)))   

  def wordAnagrams(word: Word): List[Word] =                      // pass a word and return list of 0:m word matches
     dictionaryByOcc.getOrElse(wordOccurrences(word), List[Word]())



