package funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s12 = union(s1,s2)
    val s23 = union(s2,s3)
    val s34 = union(s3,s4)
    val sall = union(union(s12,s34), s5)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remvoe the
   * @Ignore annotation.
   */
  @Ignore("not ready yet") @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
      assert(contains(sall, 1), "Sall 1")
      assert(contains(sall, 5), "Sall 5")
    }
  }

  @Test def `diff test`: Unit = {
    new TestSets {
      val t = diff(s1, s2)
      val t2 = diff(s12, s2)
      val t3 = diff(s23, s34)
      assert(contains(t, 1), "Diff 1")
      assert(contains(t2, 1), "Diff 2")
      assert(contains(t3, 2), "Diff 3")
    }
  }

  @Test def `intersect test`: Unit = {
    new TestSets {
      val t = intersect(s1, s2)
      val t2 = intersect(s12, s2)
      assert(!contains(t, 1), "intersect 1")
      assert(contains(t2, 2), "intersect 2")
    }
  }

  @Test def `filter test`: Unit = {
    new TestSets {
      var evenf = (x:Int) => x % 2 == 0
      val t = filter(s12, evenf)
      val t2 = filter(sall, evenf)
      assert(contains(t, 2), "filter 1")
      assert(contains(t2, 2), "filter 2")
      assert(contains(t2, 4), "filter 3")
    }
  }

  @Test def `forall test`: Unit = {
    new TestSets {
      var evenf = (x:Int) => x % 2 == 0
      val t = forall(union(s2,s4), evenf)
      val t2 = forall(s12, evenf)
      assert(t == true, "forall 1")
      assert(t2==false, "forall 2")
    }
  }

  @Test def `exists test`: Unit = {
    new TestSets {
      var evenf = (x:Int) => x % 2 == 0
      val t = exists(union(s2,s4), evenf)
      val t2 = exists(s12, evenf)
      assert(t == true, "exists 1")
      assert(t2== true, "exists 2")
    }
  }
  @Test def `map test`: Unit = {
    new TestSets {
      var double = (x:Int) => x * 2
      val t = map(union(s2,s4), double)
      val t2 = map(s3, double)
      assert(contains(t, 4), "filter 1a")
      assert(contains(t, 8), "filter 1b")
      assert(contains(t2, 6), "filter 2")
    }
  }
  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
