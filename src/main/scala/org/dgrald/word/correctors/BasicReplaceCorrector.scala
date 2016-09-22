package org.dgrald.word.correctors

import org.dgrald.StringUtils

/**
  * Created by dylangrald on 9/19/16.
  */
object BasicReplaceCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val toReplaceMap = Map("transthoracic echocardiogram" -> "TTE",
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
      "preformed" -> "pre-formed"
    )

    val caseIrrelevantToReplace = Map(
      "FU" -> "follow-up",
      "x-ray" -> "X-ray",
      "Kaplan Meier" -> "Kaplan-Meier",
      "New York Heart Association" -> "NYHA"
    )

    val input2 = toReplaceMap.toList.foldRight(List(input))((pair, outputs) => pair match {
      case (toReplace: String, replacement: String) => {
        val firstChar = toReplace.head
        val regex = s"\\b[${firstChar.toUpper}$firstChar]${toReplace.tail}\\b".r
        val output = regex.replaceAllIn(outputs.head, regexMatch => {
          if(StringUtils.isCapitalized(regexMatch.toString)) {
            StringUtils.capitalize(replacement)
          } else {
            replacement
          }
        })
        output +: outputs
      }
    }).head

    caseIrrelevantToReplace.toList.foldRight(List(input2))((pair, outputs) => pair match {
      case (toReplace: String, replacement: String) => {
        val regex = s"\\b$toReplace\\b".r
        regex.replaceAllIn(outputs.head, replacement) +: outputs
      }
    }).head
  }
}
