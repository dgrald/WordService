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

  "Should eliminate plus or minus symbol" >> {
    Fragment.foreach(plusOrMinusSymbols) { plusOrMinus =>
      s"in 'Something 12.23 $plusOrMinus 22.29 something'"  ! {
        val input = s"Something 12.23 $plusOrMinus 22.29 something"

        val output = PlusAndMinusCorrector.correct(input)

        output must_== "Something 12.23 something"
      }
    }
  }
}
