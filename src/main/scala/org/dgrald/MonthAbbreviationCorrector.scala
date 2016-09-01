package org.dgrald

/**
  * Created by dylangrald on 8/31/16.
  */
object MonthAbbreviationCorrector extends Corrector {
  override def correct(input: String): String = {
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

    mapOfMonthAbbreviations.foldRight(List(input))((map, list) => map match {
      case (key, value) => list.head.replaceAll(s"\\b$key", value) +: list
    }).head
  }
}
