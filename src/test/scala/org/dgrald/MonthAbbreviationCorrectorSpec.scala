package org.dgrald

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
}
