package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
trait Corrector {
  def correct(input: String, otherInstructions: List[Any] = List()): String
}

object WordServiceCorrector {
  def correct(input: String, createNewLines: Boolean, removeNewLines: Boolean, addAsterisks: Boolean, otherInstructions: List[Any]): String = {
    val correctors: List[Corrector] = getCorrectors(createNewLines, removeNewLines, addAsterisks)

    correctors.foldRight(List(input))((corrector, resultList) => {
      val corrected = corrector.correct(resultList.head, otherInstructions)
      corrected +: resultList
    }).head
  }

  def getCorrectors(createNewLines: Boolean, removeNewLines: Boolean, addAsterisks: Boolean): List[Corrector] = {
    val afterRemovingNewLines = List(NumberCommaCorrector, DateCorrector, BasicReplaceCorrector, PreWordCorrector, HyphenCorrector, VersusCorrector, ComparisonSymbolCorrector, FindAndReplaceCorrector, BulletCorrector, MultipleSpaceCorrector)
    val beforeAddingNewLines = if(removeNewLines) afterRemovingNewLines ++ List(NewLineAndTabRemoverCorrector) else afterRemovingNewLines
    val afterAddingNewLines = List(CaseInsensitiveCorrector, WordToOrdinalCorrector, WordToNumberCorrector, PlusAndMinusCorrector, PercentageSpaceCorrector, MonthAbbreviationCorrector, PeriodRemoverCorrector)
    if (createNewLines) {
      if(addAsterisks) {
        afterAddingNewLines ++ List(AsteriskCorrector, NewLineAdderCorrector) ++ beforeAddingNewLines
      } else {
        afterAddingNewLines ++ List(NewLineAdderCorrector) ++ beforeAddingNewLines
      }
    } else {
      if(addAsterisks) {
        afterAddingNewLines ++ List(AsteriskCorrector) ++ beforeAddingNewLines
      } else {
        afterAddingNewLines ++ beforeAddingNewLines
      }
    }
  }
}
