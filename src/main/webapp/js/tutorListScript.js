document.addEventListener('DOMContentLoaded', function() {

    const shift = 200;
    const listSize = document.getElementById("tutors-list-size").value;
    const sliderElementsCount = 4;

    sliderLine = document.querySelector('.slider-line');
    if (sliderLine != null) {
        let change = 0;
        limit = shift*listSize;
        sliderLine.style.width = limit + 'px';

        document.getElementById('right').addEventListener('click', function() {
            change = change + shift;
            if (change > limit) {
                change = -shift*sliderElementsCount;
                sliderLine.style.transition = "all ease 0s";
            } else {
                sliderLine.style.transition = "all ease 1s";
            }
            sliderLine.style.left = -change + 'px';
        });

        document.getElementById('left').addEventListener('click', function () {
            change = change - shift;
            if (change < -shift*sliderElementsCount) {
                sliderLine.style.transition = "all ease 0s";
                change = shift*listSize;
            } else {
                sliderLine.style.transition = "all ease 1s";
            }
            sliderLine.style.left = -change + 'px';

        });

    }

});