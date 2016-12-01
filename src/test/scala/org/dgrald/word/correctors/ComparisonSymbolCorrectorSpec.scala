package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/28/16.
  */
class ComparisonSymbolCorrectorSpec extends Specification {

  val lessThanOrEqualToUnicode = "\u2264"
  val greaterThanOrEqualToUnicode = "\u2265"

  val comparisonSymbols = List(">=", greaterThanOrEqualToUnicode, "<=", lessThanOrEqualToUnicode, ">", "<", "=")

  "Should replace comparison symbol followed by a space with a space" >> {
    Fragment.foreach(comparisonSymbols) { symbol =>
      "with symbol " + symbol ! {
        val input = s"Something with $symbol 1000"
        ComparisonSymbolCorrector.correct(input) must_== s"Something with ${symbol}1000"
      }
    }
  }

  "Should replace 'Something>2' with 'Something >2'" in {
    val input = "Something>2"

    ComparisonSymbolCorrector.correct(input) must_== s"Something >2"
  }

  "Should not change letter followed by comparison symbol followed by number" in {
    val testCases = List("n<2", "z>2.54", "g=56")
    Fragment.foreach(testCases) { testCase =>
      val input = s"Something with $testCase in it."
      s"'$input'" ! {
        ComparisonSymbolCorrector.correct(input) must_== input
      }
    }
  }

  "Should not change letter followed by comparison symbol followed by number" in {
    val testCases = List("n<2", "z>2.54", "g=56")
    Fragment.foreach(testCases) { testCase =>
      val input = s"$testCase"
      s"'$input'" ! {
        ComparisonSymbolCorrector.correct(input) must_== input
      }
    }
  }

  "Should not change comparison symbol followed by number" in {
    val testCases = List("<2", ">2.54", "=56")
    Fragment.foreach(testCases) { testCase =>
      val input = s"$testCase"
      s"'$input'" ! {
        ComparisonSymbolCorrector.correct(input) must_== input
      }
    }
  }

  "Should correct '> 2' to '>2'" in {
    val input = "> 2"

    ComparisonSymbolCorrector.correct(input) must_== ">2"
  }
}
