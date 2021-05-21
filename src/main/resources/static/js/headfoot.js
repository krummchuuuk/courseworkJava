window.onload = (function() {
    var header_text = "<nav class=\"navbar navbar-expand-lg navbar-light\"> " + 
        "<div class=\"container-fluid\">" +
            "<a href=\"/\" class=\"navbar-brand ms-5\">" + 
                "<img src=\"../images/logo.png\" class=\"logo img-fluid\">" +
            "</a>" + 
            "<button type=\"button\" class=\"navbar-toggler justify-content-center me-4\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarContent\" area-controls=\"navbarContent\" area-expanded=\"false\">" +
                "<span class=\"navbar-toggler-icon\"></span>" + 
            "</button>" + 
            "<div class=\"collapse navbar-collapse justify-content-end me-4\" id=\"navbarContent\">" +
                "<ul class=\"navbar-nav\">" +
                    "<li class=\"nav-item ms-4\">" + 
                        "<a href=\"/\" class=\"nav-link\">ГЛАВНАЯ</a>" +
                    "</li>" +
                    "<li class=\"nav-item ms-4\">" +
                        "<a href=\"/catalog\" class=\"nav-link\">КАТАЛОГ</a>" +
                    "</li>" +
                    "<li class=\"nav-item ms-4\">" +
                        "<a href=\"about\" class=\"nav-link\">МЫ</a>" +
                    "</li>" +
                    "<li class=\"nav-item ms-4\">" +
                        "<a href=\"/delivery\" class=\"nav-link\">ДОСТАВКА</a>" +
                    "</li>" +
                    "<li class\"nav-item ps-5\">" + 
                        "<a href=\"/userpage\" class=\"nav-link\"><img src=\"../images/user.svg\"</a>" + 
                    "</li>" +
                    "<li class\"nav-item ps-5\">" + 
                        "<a href=\"/cart\" class=\"nav-link\"><img src=\"../images/shopping-cart.svg\"</a>" + 
                    "</li>" +
                "</ul>" +
            "</div>" +
        "</div>" +
    "</nav>";
    document.getElementsByClassName('head')[0].innerHTML = header_text;

    var footer_text = " <footer class=\"pt-4 pb-4 mt-3\"> <div class=\"d-flex row justify-content-center\"> <img src=\"../images/white-ico.png\" style=\"width: 6%;\"> </div></footer>";
    document.getElementsByClassName('foot')[0].innerHTML = footer_text;
})