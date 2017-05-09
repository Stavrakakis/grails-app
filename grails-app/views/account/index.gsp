<html>
    <head>
        <title>Account Summary</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    <h1>Account Summary</h1>
    <g:each in="${accountSummaries}">
        <div class="account">
        
            <h2 class="account-title">Account: ${it.name}</h2>
            <div><strong>Balance: £${it.balance}</strong></div>
            <br/>
            <div>
            <g:link action="transactions" id="${it.id}">[View Transactions]</g:link>
            </div>

        </div>
    </g:each>
    </body>
</html>
