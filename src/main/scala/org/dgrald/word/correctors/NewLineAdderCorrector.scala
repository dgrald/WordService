package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/30/16.
  */
object NewLineAdderCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {

    def fixRecursively(output: String, remaining: List[String]): String = remaining match {
      case List() => output
      case List(oneItem) => s"$output $oneItem"
      case first +: second +: rest =>
        if(first.endsWith(".") && second.matches("[A-Z].*")) {
          val listOfAbbreviationsToIgnore = Constants.exceptionsToTrailingPeriod
          if(!listOfAbbreviationsToIgnore.contains(first)) {
            return fixRecursively(s"$output $first\n$second", rest)
          }
        }
        fixRecursively(s"$output $first", List(second) ++ rest)
    }

    fixRecursively("", input.split(" ").toList).trim()
  }
}
