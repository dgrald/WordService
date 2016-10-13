function clearTextAreas() {
    document.getElementById("inputtextarea").value = "";
    document.getElementById("outputtextarea").value = "";
    selectCorrectTextArea();
}

function selectCorrectTextArea() {
    if(document.getElementById("outputtextarea").value) {
        document.getElementById("outputtextarea").select();
    } else {
        document.getElementById("inputtextarea").select();
    }
}

function onWordDocSubmit() {
    document.getElementById("worddoccontents").value = document.getElementById("outputtextarea").value;
}

function onImageSubmit() {
    getCorrectionOptions("image");
}

function onPdfSubmit() {
    getCorrectionOptions("pdf");
}

function getCorrectionOptions(suffix) {
    document.getElementById("removenewlines" + suffix).value = getCheckboxValue(document.getElementById("removenewlinesid").checked);
    document.getElementById("linebreaks" + suffix).value = getCheckboxValue(document.getElementById("linebreaksid").checked);
    document.getElementById("asterisks" + suffix).value = getCheckboxValue(document.getElementById("asterisksid").checked);
}

function getCheckboxValue(checked) {
    if(checked) {
        return "on";
    } else {
        return "off";
    }
}