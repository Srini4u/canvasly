<#if loggedInToConnectedApp == true>
    <br>
    &nbsp; Radius Cloud : Currently Logged In
    <br>
    &nbsp; Oauth Configurtion
    <br>
        &nbsp;&nbsp; Id   : ${oauthConfigurationId}
    <br>
        &nbsp;&nbsp; Code : ${oauthConfigurationCode}
    <br>
        &nbsp;&nbsp; Access Token : ${oauthConfigurationAccessToken}
    <br>
        &nbsp;&nbsp; Refres Token : ${oauthConfigurationRefreshToken}
    <br>
        &nbsp;&nbsp; Instance URL : ${oauthConfigurationInstanceURL}
    <br>
        &nbsp;&nbsp; Token Id : ${oauthConfigurationInstanceTokenId}
<br>
<br>
<br>
        Need to Disconnet ? <a href="/radius.admin.disconnect"> disconnect </a>


<#else>
    <br>
    &nbsp;Radius Cloud : <a href="${salesforceLoginURL}">Login</a> to Salesforce

</#if>


<br>
<br>
<br>
<br>
