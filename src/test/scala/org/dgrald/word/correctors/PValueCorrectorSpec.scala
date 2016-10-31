package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 10/31/16.
  */
class PValueCorrectorSpec extends Specification {

  val lessThanOrEqualToUnicode = "\u2264"
  val greaterThanOrEqualToUnicode = "\u2265"
  val comparisonSymbols = List(">", "<", "=", ">=", "<=", lessThanOrEqualToUnicode, greaterThanOrEqualToUnicode)

  "Should correct" >> {
    Fragment.foreach(comparisonSymbols) { testCase =>
      val input = s"Something with p ${testCase}2 in it."
      val expected = s"Something with p${testCase}2 in it."
      s"'$input' to '$expected'" ! {
        val output = PValueCorrector.correct(input)

        output must_== expected
      }
    }
  }

  "Should correct" >> {
    Fragment.foreach(comparisonSymbols) { testCase =>
      val input = s"Something with p $testCase.23 in it."
      val expected = s"Something with p$testCase.23 in it."
      s"'$input' to '$expected'" ! {
        val output = PValueCorrector.correct(input)

        output must_== expected
      }
    }
  }

  "Should correct" >> {
    Fragment.foreach(comparisonSymbols) { testCase =>
      val input = s"Something with P ${testCase}45 in it."
      val expected = s"Something with p${testCase}45 in it."
      s"'$input' to '$expected'" ! {
        val output = PValueCorrector.correct(input)

        output must_== expected
      }
    }
  }
}

