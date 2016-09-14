package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/14/16.
  */
class WordToOrdinalCorrectorSpec extends Specification {

  val wordToOrdinalMap = Map(
    "first" -> "1st",
    "second" -> "2nd",
    "third" -> "3rd",
    "fourth" -> "4th",
    "fifth" -> "5th",
    "sixth" -> "6th",
    "seventh" -> "7th",
    "eighth" -> "8th",
    "ninth" -> "9th",
    "tenth" -> "10th"
  )

  "Should correct" >> {
    Fragment.foreach(wordToOrdinalMap.keys.toList) {word =>
      val ordinal = wordToOrdinalMap(word)
      s"$word to $ordinal" ! {
        val input = s"Something with ${word} and ${StringUtils.capitalize(word)} in it."
        WordToOrdinalCorrector.correct(input) must_== s"Something with $ordinal and $ordinal in it."
      }
    }
  }

  "Should not replace ordinal words if they are a subset of a larger word" in {
    val input = "Something with secondly in it."

    WordToOrdinalCorrector.correct(input) must_== input
  }

  "Should correct sentence with multiple ordinal words in it" in {
    val input = "Something with first, second, and third in it."

    WordToOrdinalCorrector.correct(input) must_== "Something with 1st, 2nd, and 3rd in it."
  }
}
