document.addEventListener('DOMContentLoaded', function() {

    signIn = document.getElementById('sign-in-menu');
    back = document.getElementById('gray');

    if (signIn != null) {
        document.getElementById('nav-sign-in').addEventListener('click', function() {
            signIn.style.display = 'block';
            back.style.display = 'block';
        });

        back.addEventListener('click', function() {
            if (back.style.display == 'block') {
                back.style.display = 'none';
                signIn.style.display = 'none'
            }
        });

    }

    if (document.querySelector('.alert') != null) {
        signIn.style.display = 'block';
        back.style.display = 'block';
    };


})


