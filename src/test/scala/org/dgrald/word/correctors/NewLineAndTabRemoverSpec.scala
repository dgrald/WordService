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
      "Something that has a hyphenated-\nword in it,\nman.",
      "Something that has a hyphenated\n-\nword in it,\nman.",
      "Something that has a hyphenated\n\t-\t\nword in it,\nman.")) { testCase =>
      s"'$testCase' to '$expected'" ! {
        val output = NewLineAndTabRemoverCorrector.correct(testCase)

        output must_== expected
      }
    }
  }

  "Should correct" >> {
    val expected = "Something with a comma, man."

    Fragment.foreach(List(
      "Something with a comma,\nman.",
      "Something with a comma\n,\nman.",
      "Something with a comma\t\n,\t\nman.")) { testCase =>
      s"'$testCase' to '$expected'" ! {
        val output = NewLineAndTabRemoverCorrector.correct(testCase)

        output must_== expected
      }
    }
  }

  "Should not add a space after a comma if the comma separates digits" in {
    val input = "Something 3,690 mm."

    val output = NewLineAndTabRemoverCorrector.correct(input)

    output must_== input
  }
}
