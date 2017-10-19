<script src="/js/api.js"></script>
<script src="/js/canvas-all.js"></script>
<script src="/js/chatter-talk.js"></script>
<script src="/js/json2.js"></script>
<script src="/js/publisher.js"></script>

<div class="canvasContainer">
    signedRequest <textarea id="signedRequest" rows="5" cols="100"> ${escapedSignedRequest} </textarea> <br/><br/><hr/><br/>
    <input id="canvasPublishMessage" value=""/> <button onclick="canvasPublish( document.getElementById('canvasPublishMessage').value )" > Publish </button> <br/><br/><hr/><br/>
    Subcribed Message <div id="canvasSubscribed"/> <br/><br/><hr/><br/>
</div>


<!-- init block -->
<script>
    var signedRequest = JSON.parse( '${signedRequest}' );

    Sfdc.canvas(function() {
        console.log( signedRequest );
        Sfdc.canvas.oauth.token(signedRequest.oauthToken);
        console.log( ' username ' + signedRequest.context.user.fullName );
    });
</script>

<!-- global var initialization block -->
<script>
    var namespacePrefix = '';   // e.g. test__
    var vfTopic = 'vfTopic';
    var canvasTopic = 'canvasTopic';
</script>


<!-- publish block -->
<script>
    function canvasPublish(message) {
        Sfdc.canvas.client.publish( signedRequest.client,{
            name :  namespacePrefix  + canvasTopic,
            payload : message
        });
    }
</script>

<!-- subscribe block -->


<!-- resize block -->


<!-- api block -->


<!-- chatter block -->