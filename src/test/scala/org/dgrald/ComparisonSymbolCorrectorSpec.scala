package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/28/16.
  */
class ComparisonSymbolCorrectorSpec extends Specification {

  val lessThanOrEqualToUnicode = "\u2264"
  val greaterThanOrEqualToUnicode = "\u2265"

  val comparisonSymbols = List(">=", greaterThanOrEqualToUnicode, "<=", lessThanOrEqualToUnicode, ">", "<")

  "Should replace comparison symbol followed by a space with a space" >> {
    Fragment.foreach(comparisonSymbols) { symbol =>
      "with symbol " + symbol ! {
        val input = s"Something with $symbol 1000"
        ComparisonSymbolCorrector.correct(input) must_== s"Something with ${symbol}1000"
      }
    }
  }

}
