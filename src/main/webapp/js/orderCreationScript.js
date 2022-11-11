
document.addEventListener('DOMContentLoaded', function() {

    textarea = document.getElementById("textarea1");

    if (textarea != null) {
        textarea.addEventListener("input", function (){
            maxLength = 120;
            document.getElementById("remaining-symblos").firstChild.data = "Left " + (maxLength - textarea.value.length) + "/120";
        });
    }

    modal = new bootstrap.Modal(document.getElementById("answer-modal"));
    if (modal != null) {
        modal.show();
    }


})


