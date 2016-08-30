package org.dgrald

/**
  * Created by dylangrald on 8/30/16.
  */
object NewLineCorrector extends Corrector {
  override def correct(input: String): String = {

    def fixRecursively(output: String, remaining: List[String]): String = remaining match {
      case List() => output
      case List(oneItem) => s"$output $oneItem"
      case first +: second +: rest => {
        if(first.endsWith(".") && second.matches("[A-Z].*")) {
          val listOfAbbreviationsToIgnore = List("vs.", "U.S.")
          if(!listOfAbbreviationsToIgnore.contains(first)) {
            return fixRecursively(s"$output ${first.substring(0, first.length - 1)}\n$second", rest)
          }
        }
        fixRecursively(s"$output $first", List(second) ++ rest)
      }
    }

    fixRecursively("", input.split(" ").toList).trim()
  }
}
