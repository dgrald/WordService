package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylan
  * grald on 8/28/16.
  */
class NewLineAndTabRemoverSpec extends Specification {

  "Should replace tabs and new lines with spaces" in {
    val input = "Something with multiple\rnew lines\n\nand \ttabs \tin a string."

    val output = NewLineAndTabRemoverCorrector.correct(input)

    output must_== "Something with multiple new lines  and  tabs  in a string."
  }

  "Should correct" >> {
    val expected = "Something that has a hyphenated-word in it, man."

    Fragment.foreach(List(
      "Something that has a hyphenated\n \t-\t \nword in it,\nman.")) { testCase =>
      s"'$testCase' to '$expected'" ! {
        val output = NewLineAndTabRemoverCorrector.correct(testCase)

        output must_== expected
      }
    }
  }
}
