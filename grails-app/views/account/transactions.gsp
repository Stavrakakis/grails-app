<html>
    <head>
        <title>Account Transactions</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    <h1>Account Transactions</h1>
    <h3 class="account-title">Account: ${account.name}, Balance: £${account.balance}</h3>
    <table id="transactions">
    <thead>
        <tr>
            <td>Date</td>
            <td>From</td>
            <td>To</td>
            <td>Amount</td>
        </tr>
    </thead>
    <g:each in="${transactions}">
        <tr>
            <td>${it.dateCreated.format('yyyy-MM-dd')}</td>
            <td>${it.debitAccount.name}</td>
            <td>${it.creditAccount.name}</td>
            <td><g:if test="${it.debitAccount == account}">-</g:if>£${it.value}</td>
        </tr>
    </g:each>
    </table>
    <div><g:link action="index">Back to accounts</g:link></div> 
    </body>
</html>
