function calculateDivider() {
    var dividerValue = 4;
    if ($(this).width() <= 480) {
        dividerValue = 1;
    } else if ($(this).width() <= 767) {
        dividerValue = 2;
    } else if ($(this).width() <= 980) {
        dividerValue = 3;
    }
    return dividerValue;
}


$(document).ready(function () {
    "use strict";
    $(window).load(function () {
        var container = $('.gallery');
        var dividerValue = calculateDivider();
        var containerWidth = $(container).width() - 20;
        var columnWidth = containerWidth / dividerValue;
        $(container).isotope({
            resizable: true,
            masonry: {
                columnWidth: columnWidth
            }
        });

        $(window).smartresize(function () {
            var dividerValue = calculateDivider();
            var containerWidth = $(container).width() - 20;
            var columnWidth = containerWidth / dividerValue;
            $(container).isotope({
                masonry: {
                    columnWidth: columnWidth
                }
            });
        });

        var $optionSets = $('.options .gallery-option-set'),
                $optionLinks = $optionSets.find('a');

        $optionLinks.click(function () {
            var $this = $(this);
            if ($this.hasClass('active')) {
                return false;
            }
            var $optionSet = $this.parents('.gallery-option-set');
            $optionSet.find('.active').removeClass('active');
            $this.addClass('active');

            var options = {};
            var key = $optionSet.attr('data-option-key');
            var value = $this.attr('data-option-value');
            value = value === 'false' ? false : value;
            options[ key ] = value;
            $(container).isotope(options);
            return false;
        });
    });
});