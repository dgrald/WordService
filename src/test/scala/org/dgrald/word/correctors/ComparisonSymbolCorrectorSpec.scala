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

  "Should correct '> 2' with '>2'" in {
    val input = "> 2"

    ComparisonSymbolCorrector.correct(input) must_== ">2"
  }
}
