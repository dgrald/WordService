package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
trait Corrector {
  def correct(input: String, otherInstructions: List[Any] = List()): String
}

object WordServiceCorrector {
  def correct(input: String, createNewLines: Boolean, removeNewLines: Boolean, otherInstructions: List[Any]): String = {
    val correctors: List[Corrector] = getCorrectors(createNewLines, removeNewLines)

    correctors.foldRight(List(input))((corrector, resultList) => {
      corrector.correct(resultList.head, otherInstructions) +: resultList
    }).head
  }

  def getCorrectors(createNewLines: Boolean, removeNewLines: Boolean): List[Corrector] = {
    val afterRemovingNewLines = List(PreWordCorrector, FollowUpCorrector, HyphenCorrector, VersusCorrector, ComparisonSymbolCorrector, FindAndReplaceCorrector, MultipleSpaceCorrector)
    val beforeAddingNewLines = if(removeNewLines) afterRemovingNewLines ++ List(RemoveNewLineAndTabCorrector) else afterRemovingNewLines
    val afterAddingNewLines = List(WordToOrdinalCorrector, WordToNumberCorrector, PlusAndMinusCorrector, PercentageSpaceCorrector, MonthAbbreviationCorrector)
    if (createNewLines) {
      afterAddingNewLines ++ List(NewLineCorrector) ++ beforeAddingNewLines
    } else {
      afterAddingNewLines ++ beforeAddingNewLines
    }
  }
}
