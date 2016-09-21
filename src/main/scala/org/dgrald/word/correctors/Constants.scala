package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/19/16.
  */
object Constants {
  def exceptionsToTrailingPeriod: List[String] = List("vs.", "U.S.", "St.", "Dr.")

  def mapOfMonthAbbreviations: List[(String, String)] = List(
    ("January", "Jan."),
    ("February", "Feb."),
    ("March", "March"),
    ("April", "April"),
    ("May", "May"),
    ("June", "June"),
    ("July", "July"),
    ("August", "Aug."),
    ("September", "Sept."),
    ("October", "Oct."),
    ("November", "Nov."),
    ("December", "Dec.")
  )
}
