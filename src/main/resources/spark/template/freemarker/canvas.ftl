<div class="canvasContainer">
    signedRequest
    <textarea id="signedRequest" rows="10" cols="100"> ${escapedSignedRequest} </textarea>
</div>


<script>

    Sfdc.canvas(function() {
        var signedRequest = JSON.parse( '${signedRequest}' );
        console.log( signedRequest );
        Sfdc.canvas.oauth.token(signedRequest.oauthToken);
        console.log( ' username ' + signedRequest.context.user.fullName );
    });


</script>
