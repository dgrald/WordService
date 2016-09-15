package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/30/16.
  */
class NewLineCorrectorSpec extends Specification {

  "Should add a new line when there is word followed by a period followed by a space and another word" in {
    val input = "Something sentence one. Something sentence two."

    val output = NewLineCorrector.correct(input)

    output must_== "Something sentence one\nSomething sentence two"
  }

  "Should not add a new line when there is a period followed by a lower case letter" in {
    val input = "Something one vs. something two."

    val output = NewLineCorrector.correct(input)

    output must_== "Something one vs. something two"
  }

  val abbreviationsToSkip = List("vs.", "U.S.", "St.", "Dr.")

  "Should not add a new line" >> {
    Fragment.foreach(abbreviationsToSkip) { abbreviation =>
      s"$abbreviation is followed by a capitalized word" ! {
        val input = s"Something with $abbreviation Advil"
        NewLineCorrector.correct(input) must_== input
      }
    }
  }
}
