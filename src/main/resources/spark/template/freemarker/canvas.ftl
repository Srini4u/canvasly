<script src="/js/api.js"></script>
<script src="/js/canvas-all.js"></script>
<script src="/js/chatter-talk.js"></script>
<script src="/js/json2.js"></script>
<script src="/js/publisher.js"></script>

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
