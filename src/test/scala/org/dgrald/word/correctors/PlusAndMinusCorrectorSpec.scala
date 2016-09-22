package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/29/16.
  */
class PlusAndMinusCorrectorSpec extends Specification {

  val plusOrMinusUnicode = "\u00B1"

  val plusOrMinusSymbols = List("+/-", plusOrMinusUnicode)

  "Should eliminate plus or minus symbol" >> {
    Fragment.foreach(plusOrMinusSymbols) { plusOrMinus =>
      s"in 'Something 1200 $plusOrMinus 22 something'"  ! {
        val input = s"Something 1200 $plusOrMinus 22 something"

        val output = PlusAndMinusCorrector.correct(input)

        output must_== "Something 1200 something"
      }
    }
  }

  "Should eliminate plus or minus symbol and plus or minus number" >> {
    Fragment.foreach(plusOrMinusSymbols) { plusOrMinus =>
      s"in 'Something 12.23 $plusOrMinus 22.29 something'"  ! {
        val input = s"Something 12.23 $plusOrMinus 22.29 something"

        val output = PlusAndMinusCorrector.correct(input)

        output must_== "Something 12.23 something"
      }
    }
  }

  "Should eliminate plus or minus symbol and plus or minus number" in {
    Fragment.foreach(plusOrMinusSymbols) { plusOrMinus =>
      s"in 'Something 8.9${plusOrMinus}8 years.'"  ! {
        val input = s"Something 8.9${plusOrMinus}8 years."

        val output = PlusAndMinusCorrector.correct(input)

        output must_== "Something 8.9 years."
      }
    }
  }

  "Should replace" >> {
    Fragment.foreach(List("4,300", "2,300,999")) { testCase =>
      s"'4444 +/- $testCase mm' with '4444 mm'" ! {
        val input = s"Something with 4444 +/- $testCase mm"

        val output = PlusAndMinusCorrector.correct(input)

        output must_== "Something with 4444 mm"
      }

    }
  }
}
