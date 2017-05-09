<!DOCTYPE html>
    <head>
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <g:layoutHead/>
    </head>
    <body>
    <div id="fixedheader">
        <g:img class="site-logo" dir="images" file="logo.svg" />
    </div>
        <div id="menu">
            <g:link controller="account">Accounts</g:link>
            <g:link controller="payment">Pay</g:link>
        </div>
        <g:layoutBody/>
    </body>
</html>