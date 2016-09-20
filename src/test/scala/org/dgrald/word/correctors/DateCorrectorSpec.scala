package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/19/16.
  */
class DateCorrectorSpec extends Specification {
  val mapOfMonths = Map(
    "1" -> "Jan.",
    "2" -> "Feb.",
    "3" -> "March",
    "4" -> "April",
    "5" -> "May",
    "6" -> "June",
    "7" -> "July",
    "8" -> "Aug.",
    "9" -> "Sept.",
    "10" -> "Oct.",
    "11" -> "Nov.",
    "12" -> "Dec."
  )

  "Should replace" >> {
    Fragment.foreach(mapOfMonths.toList) {
      case (toReplace: String, replacement: String) => {
        s"'$toReplace/ 2008' with '$replacement 2008'" ! {
          val input = s"Something with $toReplace/ 2008 in it."

          val output = DateCorrector.correct(input)

          output must_== s"Something with $replacement 2008 in it."
        }
      }
    }
  }

  "Should replace" >> {
    Fragment.foreach(mapOfMonths.toList.filter(pair => pair._1.length == 1)) {
      case (toReplace: String, replacement: String) => {
        s"'0$toReplace/ 2008' with '$replacement 2008'" ! {
          val input = s"Something with 0$toReplace/ 2008 in it."

          val output = DateCorrector.correct(input)

          output must_== s"Something with $replacement 2008 in it."
        }
      }
    }
  }

  "Should replace" >> {
    Fragment.foreach(mapOfMonths.toList) {
      case (toReplace: String, replacement: String) => {
        s"'$toReplace/2008' with '$replacement 2008'" ! {
          val input = s"Something with $toReplace/2008 in it."

          val output = DateCorrector.correct(input)

          output must_== s"Something with $replacement 2008 in it."
        }
      }
    }
  }

  "Should replace" >> {
    Fragment.foreach(mapOfMonths.toList.filter(pair => pair._1.length == 1)) {
      case (toReplace: String, replacement: String) => {
        s"'0$toReplace/2008' with '$replacement 2008'" ! {
          val input = s"Something with 0$toReplace/2008 in it."

          val output = DateCorrector.correct(input)

          output must_== s"Something with $replacement 2008 in it."
        }
      }
    }
  }

}
