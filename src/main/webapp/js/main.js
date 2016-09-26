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