$(document).ready(function () {
    "use strict";
    $('.add-sticky').click(function () {
        $.gritter.add({
            title: 'This is a sticky notice!',
            text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus lacus ut lectus rutrum placerat. ',
            image: 'application/views/resources/color_admin/img/user-2.jpg',
            sticky: true,
            time: '',
            class_name: 'my-sticky-class'
        });
        return false;
    });

    $('.add-regular').click(function () {
        $.gritter.add({
            title: 'This is a regular notice!',
            text: 'This will fade out after a certain amount of time. Sed tempus lacus ut lectus rutrum placerat. ',
            image: 'application/views/resources/color_admin/img/user-3.jpg',
            sticky: false,
            time: ''
        });
        return false;
    });
    
    $('.add-max').click(function () {
        $.gritter.add({
            title: 'This is a notice with a max of 3 on screen at one time!',
            text: 'This will fade out after a certain amount of time. Sed tempus lacus ut lectus rutrum placerat. ',
            sticky: false,
            image: 'application/views/resources/color_admin/img/user-4.jpg',
            before_open: function () {
                if ($('.gritter-item-wrapper').length === 3) {
                    return false;
                }
            }
        });
        return false;
    });
    
    $('.add-without-image').click(function () {
        $.gritter.add({
            title: 'This is a notice without an image!',
            text: 'This will fade out after a certain amount of time.'
        });
        return false;
    });
    
    $('.add-gritter-light').click(function () {
        $.gritter.add({
            title: 'This is a light notification',
            text: 'Just add a "gritter-light" class_name to your $.gritter.add or globally to $.gritter.options.class_name',
            class_name: 'gritter-light'
        });
        return false;
    });
    
    $('.add-with-callbacks').click(function () {
        $.gritter.add({
            title: 'This is a notice with callbacks!',
            text: 'The callback is...',
            before_open: function () {
                alert('I am called before it opens');
            },
            after_open: function (e) {
                alert("I am called after it opens: \nI am passed the jQuery object for the created Gritter element...\n" + e);
            },
            before_close: function (manual_close) {
                var manually = (manual_close) ? 'The "X" was clicked to close me!' : '';
                alert("I am called before it closes: I am passed the jQuery object for the Gritter element... \n" + manually);
            },
            after_close: function (manual_close) {
                var manually = (manual_close) ? 'The "X" was clicked to close me!' : '';
                alert('I am called after it closes. ' + manually);
            }
        });
        return false;
    });
    
    $('.add-sticky-with-callbacks').click(function () {
        $.gritter.add({
            title: 'This is a sticky notice with callbacks!',
            text: 'Sticky sticky notice.. sticky sticky notice...',
            sticky: true,
            before_open: function () {
                alert('I am a sticky called before it opens');
            },
            after_open: function (e) {
                alert("I am a sticky called after it opens: \nI am passed the jQuery object for the created Gritter element...\n" + e);
            },
            before_close: function (e) {
                alert("I am a sticky called before it closes: I am passed the jQuery object for the Gritter element... \n" + e);
            },
            after_close: function () {
                alert('I am a sticky called after it closes');
            }
        });
        return false;
    });
    
    $(".remove-all").click(function () {
        $.gritter.removeAll();
        return false;
    });
    
    $(".remove-all-with-callbacks").click(function () {
        $.gritter.removeAll({
            before_close: function (e) {
                alert("I am called before all notifications are closed.  I am passed the jQuery object containing all  of Gritter notifications.\n" + e);
            },
            after_close: function () {
                alert('I am called after everything has been closed.');
            }
        });
        return false;
    });
});