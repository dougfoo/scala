
public static void main(String args[]) {

  List<String> dictionary = Dictionary.loadDictionary();            // load file w/ valid words

  Map<String, List<String>> dictionaryByOcc = makeDictMap();

  List<String> getAnagrams(String word) {
    return dictionaryByOcc.getOrDefault(word.toLowerCase().sorted(), new List<String>());
  }

  Map<String, List<String>> makeDictMap() {
     Map<String, List<String>> map = new HashMapList<String, List<String>>();
     for (String word: dictionary) {
        String key = word.toLowerCase().sorted();
        List<String> words = map.getOrDefault(key, new List<String>());
        words.append(word)
     }   
     return map;
  }



  lazy val dictionaryByOcc: Map[List[(Char, Int)], List[Word]] =  // dict of "Odor" -> "door":["odor","door","ordo","rood"]
     dictionary.map(w=> (w.toLowerCase.sorted, w).groupBy(_c._1).map(p=>(p.))    

  def getAnagrams(word: Word): List[Word] =                      // pass a word and return list of 0:m word matches
     dictionaryByOcc.getOrElse(word.toLowerCase.sorted, List[Word]())





  def wordOccurrences(w: Word): List[(Char, Int)] =               // take "Odor" -> {d:1, o:2, r:1}
     w.toLowerCase.toList.groupBy(identity).map(p=> (p._1, p._2.size)).toList.sorted 

  lazy val dictionaryByOcc: Map[List[(Char, Int)], List[Word]] =  // dict of {d:1, o:2, r:1} -> ["odor","door","ordo","rood"]
     dictionary.map(w=> (wordOccurrences(w), w)).groupBy(_._1).map(p=>(p._1, p._2.map(x=>x._2)))   

  def getAnagrams(word: Word): List[Word] =                      // pass a word and return list of 0:m word matches
     dictionaryByOcc.getOrElse(wordOccurrences(word), List[Word]())





}