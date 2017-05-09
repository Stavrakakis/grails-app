<html>
    <head>
        <title>Make a Payment</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    <h1>Make a Payment</h1>
    <g:renderErrors bean="${transfer}" />
    <g:form name="myForm" action="transfer">
        
        <fieldset class="form">
            <div class='fieldcontain required'>
              <label for='debitAccountId'>From Account<span class='required-indicator'>*</span></label>
              <g:select name="debitAccountId" optionKey="id" from="${accountSummaries}" optionValue="name" noSelection="${['null':'Select One...']}" />
            </div>
            <div class='fieldcontain required'>
              <label for='creditAccountId'>To Account<span class='required-indicator'>*</span></label>
              <g:select name="creditAccountId" optionKey="id" from="${accountSummaries}" optionValue="name" noSelection="${['null':'Select One...']}" />
            </div>
            <div class='fieldcontain'>
              <label for='value'>Amount (£)</label>
              <input type="number" step="0.01" name="value" value="" id="value" />
            </div>
            <div class='fieldcontain'>
              <label for='value'></label>
              <input type="submit" name="create" class="save" value="Pay" id="create" />
            </div>

        <g:if test="${flash.error}">
            <div class="alert alert-error" style="display: block">${flash.error}</div>
        </g:if>
        <g:if test="${flash.success}">
            <div class="alert" style="display: block">Payment successful, <g:link controller="account">click here to go back to the account screen.</g:link></div>
        </g:if>
    </g:form>
    </body>
</html>
