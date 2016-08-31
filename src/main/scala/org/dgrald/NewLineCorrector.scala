package org.dgrald

/**
  * Created by dylangrald on 8/30/16.
  */
object NewLineCorrector extends Corrector {
  override def correct(input: String): String = {

    def stripLastCharacterIfEndsInPeriod(item: String): String = {
      if(item.endsWith(".")) {
        return stripLastCharacter(item)
      }
      item
    }

    def stripLastCharacter(item: String): String = {
      item.substring(0, item.length - 1)
    }

    def fixRecursively(output: String, remaining: List[String]): String = remaining match {
      case List() => output
      case List(oneItem) => s"$output ${stripLastCharacterIfEndsInPeriod(oneItem)}"
      case first +: second +: rest => {
        if(first.endsWith(".") && second.matches("[A-Z].*")) {
          val listOfAbbreviationsToIgnore = List("vs.", "U.S.")
          if(!listOfAbbreviationsToIgnore.contains(first)) {
            return fixRecursively(s"$output ${stripLastCharacter(first)}\n\n${stripLastCharacterIfEndsInPeriod(second)}", rest)
          }
        }
        fixRecursively(s"$output $first", List(second) ++ rest)
      }
    }

    fixRecursively("", input.split(" ").toList).trim()
  }
}
