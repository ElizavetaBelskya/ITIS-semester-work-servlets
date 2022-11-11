document.addEventListener('DOMContentLoaded', function() {

    var buttons = document.querySelectorAll('.rate');

    buttons.forEach(item => {
        item.addEventListener('click', function () {
            modal = new bootstrap.Modal(document.getElementById("rate-modal"));
            if (modal != null) {
                modal.show();
                var parentItems = document.querySelector('.stars');

                var allItems = document.querySelectorAll('.stars .star');

                var activeItems = document.querySelectorAll('.stars .star.active');

                var cStars = function (nowPos) {
                    for (var k = 0; k < allItems.length; k++) {
                        allItems[k].classList.remove('active');
                    }

                    for (var i = 0; i < nowPos + 1; i++) {
                        allItems[i].classList.toggle('active');
                    }
                }


                parentItems.addEventListener('click', function (e) {
                    var myTarget = e.target;
                    var k = allItems.length;
                    while (k--) {
                        if (allItems[k] == myTarget) {
                            var currentIndex = k;
                            break;
                        }
                    }
                    cStars(currentIndex);
                    activeItems = document.querySelectorAll('.stars .star.active');

                    document.getElementById("id-rated-tutor").value = item.value;
                    document.getElementById("star-count").value = activeItems.length.toString();
                });

                parentItems.addEventListener('mouseover', function(e) {
                        var myTarget = e.target;
                        var k = allItems.length;

                        while (k--) {
                            if (allItems[k] == myTarget) {
                                var currentIndex = k;
                                break;
                            }
                        }
                        cStars(currentIndex);
                    }
                );

                parentItems.addEventListener('mouseleave', function() {
                    cStars(activeItems.length-1);
                })
            }
        })
    })



});

