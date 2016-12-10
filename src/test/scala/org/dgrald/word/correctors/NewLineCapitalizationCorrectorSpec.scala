package org.dgrald.word.correctors

import org.specs2.mutable.Specification

/**
  * Created by dylangrald on 12/10/16.
  */
class NewLineCapitalizationCorrectorSpec extends Specification {

  "NewLineCapitalizationCorrector" should {
    "add new lines if not present" in {
      val input = "first sentence\nsecond sentence\nthird sentence\nfourth sentence, man"

      val output = NewLineCapitalizationCorrector.correct(input)

      output must_== "First sentence\nSecond sentence\nThird sentence\nFourth sentence, man"
    }

    "handle empty lines" in {
      val input = "first sentence\n\n \nsecond sentence"

      val output = NewLineCapitalizationCorrector.correct(input)

      output must_== "First sentence\n\n \nSecond sentence"
    }
  }
}
