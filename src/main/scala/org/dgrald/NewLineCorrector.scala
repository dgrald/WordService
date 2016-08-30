package org.dgrald

/**
  * Created by dylangrald on 8/30/16.
  */
object NewLineCorrector extends Corrector {
  override def correct(input: String): String = {

    def fixTailRec(output: String, remaining: List[String]): String = remaining match {
      case List() => output
      case List(oneItem) => s"$output $oneItem"
      case first +: second +: rest => {
        if(first.endsWith(".") && second.matches("[A-Z].*")) {
          val listOfAbbreviationsToIgnore = List("vs.", "U.S.")
          if(!listOfAbbreviationsToIgnore.contains(first)) {
            return fixTailRec(s"$output $first\n$second", rest)
          }
        }
        fixTailRec(s"$output $first", List(second) ++ rest)
      }
    }

    fixTailRec("", input.split(" ").toList).trim()
  }
}
