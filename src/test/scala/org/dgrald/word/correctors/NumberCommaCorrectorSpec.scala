package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/20/16.
  */
class NumberCommaCorrectorSpec extends Specification {

  "Should correct" >> {
    val testCases = List(
      ("3,69", "3.69"),
      ("0,4222", "0.4222"),
      ("0,998", "0.998")
    )
    Fragment.foreach(testCases) {
      case (in: String, expected: String) => s"'$in' to '$expected'" ! {
        val input = s"Something with $in in it."

        val output = NumberCommaCorrector.correct(input)

        output must_== s"Something with $expected in it."
      }
    }
  }

  "Should not correct" >> {
    val testCases = List(
      "3,699",
      "1,000,000",
      "9,333,333,999"
    )
    Fragment.foreach(testCases) { testCase =>
      s"'$testCase'" ! {
        val input = s"Something with $testCase in it."

        val output = NumberCommaCorrector.correct(input)

        output must_== input
      }
    }
  }

}
