<div class="canvasContainer">
    signedRequest
    <textarea id="signedRequest" rows="10" cols="100"> ${escapedSignedRequest} </textarea>
</div>


<script>
    var signedRequest = JSON.parse( '${signedRequest}' );
    console.log( signedRequest );
    signedRequest = JSON.parse( '${escapedSignedRequest}' );
    console.log( signedRequest );
</script>
