$(function() {
    $("ul.secondry-container__navi-ul li").hover(
    function() {
        $("ul.secondry-container__navi-ul-2:not(:animated)", this).stop().slideDown('fast');
    },
    function() {
        $("ul.secondry-container__navi-ul-2", this).stop().slideUp('fast');
    }
    );
});