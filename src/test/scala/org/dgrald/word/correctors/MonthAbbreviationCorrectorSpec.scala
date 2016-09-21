package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/31/16.
  */
class MonthAbbreviationCorrectorSpec extends Specification {

  val mapOfMonthAbbreviations = Map("January" -> "Jan.",
    "February" -> "Feb.",
    "March" -> "March",
    "April" -> "April",
    "May" -> "May",
    "June" -> "June",
    "July" -> "July",
    "August" -> "Aug.",
    "September" -> "Sept.",
    "October" -> "Oct.",
    "November" -> "Nov.",
    "December" -> "Dec."
  )

  "Should replace month" >> {
    Fragment.foreach(mapOfMonthAbbreviations.keys.toList) { key =>
      val value = mapOfMonthAbbreviations(key)
      s"$key with abbreviation $value" ! {
        val input = s"Something with $key in it."
        MonthAbbreviationCorrector.correct(input) must_== s"Something with $value in it."
      }
    }
  }

  "Should replace month" >> {
    Fragment.foreach(mapOfMonthAbbreviations.keys.toList) { key =>
      val value = mapOfMonthAbbreviations(key)
      s"'$key/2008' with '$value 2008'" ! {
        val input = s"Something with $key/2008 in it."
        MonthAbbreviationCorrector.correct(input) must_== s"Something with $value 2008 in it."
      }
    }
  }

  "Should not replace the '/' character if month is followed by '/' and then a non-year" in {
    val input = "Something with August/September in it."

    val output = MonthAbbreviationCorrector.correct(input)

    output must_== "Something with Aug./Sept. in it."
  }

  "Should replace multiple months" in {
    val input = "Retrospective single-center study including all patients undergoing TAVI between August/ 2007 and November/ 2015"

    val output = MonthAbbreviationCorrector.correct(input)

    output must_== "Retrospective single-center study including all patients undergoing TAVI between Aug. 2007 and Nov. 2015"
  }
}
