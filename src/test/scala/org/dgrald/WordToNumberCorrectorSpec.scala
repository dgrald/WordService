package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/14/16.
  */
class WordToNumberCorrectorSpec extends Specification {

  val numbersToWordsMap = Map(
    0 -> "zero",
    1 -> "one",
    2 -> "two",
    3 -> "three",
    4 -> "four",
    5 -> "five",
    6 -> "six",
    7 -> "seven",
    8 -> "eight",
    9 -> "nine",
    10 -> "ten"
  )

  "Should change" >> {
    Fragment.foreach(numbersToWordsMap.keys.toList) { number =>
      val word = numbersToWordsMap(number)
      s"$word to $number" ! {
        val input = s"Something with $word and ${StringUtils.capitalize(word)} in it."
        WordToNumberCorrector.correct(input) must_== s"Something with $number and $number in it."
      }
    }
  }

  "Should change 'Three and four' to '3 and 4'" in {
    val input = "Three and four."

    val output = WordToNumberCorrector.correct(input)

    output must_== "3 and 4."
  }

  "Should not should 'None'" in {
    val input = "None."

    WordToNumberCorrector.correct(input) must_== input
  }

}
