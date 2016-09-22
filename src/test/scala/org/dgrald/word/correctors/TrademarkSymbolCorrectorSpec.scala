package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/22/16.
  */
class TrademarkSymbolCorrectorSpec extends Specification {

  val registeredTrademarkUnicode = "\u00AE"
  val trademarkUnicode = "\u2122"

  "Should replace" >> {
    Fragment.foreach(List(registeredTrademarkUnicode, trademarkUnicode)) {testCase =>
      s"$testCase symbol" ! {
        val input = s"Something$testCase and Something $testCase else"

        val output = TrademarkSymbolCorrector.correct(input)

        output must_== "Something and Something else"
      }
    }
  }

}
