package org.dgrald.word.correctors

import org.dgrald.StringUtils
import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/19/16.
  */
class BasicReplaceCorrectorSpec extends Specification {

  val toReplace = Map("transthoracic echocardiogram" -> "TTE",
    "transthoracic echocardiography" -> "TTE",
    "left ventricular end systolic volume" -> "LVESV",
    "left ventricular end diastolic volume" -> "LVEDV",
    "approximately" -> "appx.",
    "left ventricular end systolic diameter" -> "LVESD",
    "trans-septal" -> "transseptal",
    "left ventricular assist device" -> "LVAD",
    "trans-apical" -> "transapical",
    "surgical aortic valve replacement" -> "SAVR",
    "multivessel" -> "multi-vessel",
    "perioperative" -> "peri-operative",
    "pts" -> "patients",
    "right bundle branch block" -> "RBBB",
    "left ventricular end diastolic diameter" -> "LVEDD",
    "left bundle branch block" -> "LBBB",
    "left ventricular end diastolic dimension" -> "LVEDD",
    "valve in valve" -> "valve-in-valve",
    "trans-femoral" -> "transfemoral",
    "left ventricular end systolic dimension" -> "LVESD",
    "periprocedurally" -> "peri-procedurally",
    "trans-aortic" -> "transaortic",
    "transcatheter aortic valve implantation" -> "TAVI",
    "prehospital" -> "pre-hospital",
    "left ventricular ejection fraction" -> "LVEF",
    "transcatheter aortic valve replacement" -> "TAVR",
    "left ventricular outflow tract" -> "LVOT",
    "transesophageal echocardiogram" -> "TEE",
    "transesophageal echocardiography" -> "TEE",
    "periprocedural" -> "peri-procedural",
    "follow up" -> "follow-up",
    "preoperatively" -> "pre-operatively",
    "preload" -> "pre-load",
    "preshaped" -> "pre-shaped",
    "premedicate" -> "pre-medicate",
    "predetermined" -> "pre-determined",
    "predilation" -> "pre-dilation",
    "presizing" -> "pre-sizing",
    "preexisting" -> "pre-existing",
    "precrimp" -> "pre-crimp",
    "predilates" -> "pre-dilates",
    "preassemble" -> "pre-assemble",
    "prespecify" -> "pre-specify",
    "preassembled" -> "pre-assembled",
    "predilatation" -> "pre-dilatation",
    "preintervention" -> "pre-intervention",
    "preclose" -> "pre-close",
    "prespecified" -> "pre-specified",
    "preprocedure" -> "pre-procedure",
    "prestenting" -> "pre-stenting",
    "prestent" -> "pre-stent",
    "preprocedural" -> "pre-procedural",
    "predilate" -> "pre-dilate",
    "precrimped" -> "pre-crimped",
    "preform" -> "pre-form",
    "preimplant" -> "pre-implant",
    "preloaded" -> "pre-loaded",
    "prestented" -> "pre-stented",
    "predilated" -> "pre-dilated",
    "preclosure" -> "pre-closure",
    "premedicated" -> "pre-medicated",
    "preimplantation" -> "pre-implantation",
    "preoperative" -> "pre-operative",
    "preformed" -> "pre-formed",
    "coronary artery bypass graft" -> "CABG",
    "coronary artery bypass grafting" -> "CABG",
    "NT-pro BNP" -> "NT-proBNP",
    "NTproBNP" -> "NT-proBNP",
    "NTpro-BNP" -> "NT-proBNP",
    "NT proBNP" -> "NT-proBNP",
    "NT pro-BNP" -> "NT-proBNP",
    "short term" -> "short-term",
    "shortterm" -> "short-term",
    "mid term" -> "mid-term",
    "midterm" -> "mid-term",
    "long term" -> "long-term",
    "longterm" -> "long-term",
    "Sapien" -> "SAPIEN"
  )

  val doNotChangeReplacementCase = Map(
    "mm Hg" -> "mmHg",
    "US" -> "U.S.",
    "United States" -> "U.S."
  )

  val caseIrrelevantToReplace = Map(
    "x-ray" -> "X-ray",
    "Kaplan Meier" -> "Kaplan-Meier",
    "New York Heart Association" -> "NYHA"
  )

  val capitalizeOnlyIfBeginningOfSentence = Map(
    "FU" -> "follow-up",
    "FMR" -> "functional MR",
    "DMR" -> "degenerative MR"
  )

  val capitalizedToReplace = toReplace.toList.map(member => member match { case (first: String, second: String) => (StringUtils.capitalize(first), StringUtils.capitalize(second))})

  "Should replace" >> {
    Fragment.foreach(toReplace.toList) {
      case (toReplace: String, replacement: String) =>
        s"'$toReplace' with '$replacement'" ! {
          val input = s"Something with $toReplace in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with $replacement in it."
        }
    }
  }

  "Should replace" >> {
    Fragment.foreach(toReplace.toList) {
      case (toReplace: String, replacement: String) =>
        s"'${StringUtils.capitalize(toReplace)}' with '$replacement'" ! {
          val input = s"Something with ${StringUtils.capitalize(toReplace)} in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with ${StringUtils.capitalize(replacement)} in it."
        }
    }
  }

  "Should replace" >> {
    Fragment.foreach(doNotChangeReplacementCase.toList) {
      case (toReplace: String, replacement: String) => {
        s"'${StringUtils.capitalize(toReplace)}' with '$replacement'" ! {
          val input = s"Something with ${StringUtils.capitalize(toReplace)} in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with $replacement in it."
        }
      }
    }
  }

  "Should replace" >> {
    Fragment.foreach(doNotChangeReplacementCase.toList) {
      case (toReplace: String, replacement: String) =>
        s"'$toReplace' with '$replacement'" ! {
          val input = s"Something with $toReplace in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with $replacement in it."
        }
    }
  }

  "Should replace" >> {
    Fragment.foreach(caseIrrelevantToReplace.toList) {
      case (toReplace: String, replacement: String) =>
        s"'$toReplace' with '$replacement'" ! {
          val input = s"Something with $toReplace in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with $replacement in it."
        }
    }
  }

  "Should replace" >> {
    Fragment.foreach(capitalizeOnlyIfBeginningOfSentence.toList) {
      case (toReplace: String, replacement: String) =>
        s"'$toReplace' with '$replacement'" ! {
          val input = s"Something with $toReplace in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with $replacement in it."
        }
    }
  }

  "Should replace" >> {
    val capitalized = capitalizeOnlyIfBeginningOfSentence.toList.map(e => (StringUtils.capitalize(e._1), StringUtils.capitalize(e._2)))
    Fragment.foreach(capitalized) {
      case (toReplace: String, replacement: String) =>
        s"'$toReplace' with '$replacement'" ! {
          val input = s"Something at the start\n$toReplace in it"

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something at the start\n$replacement in it"
        }
    }
  }

}
