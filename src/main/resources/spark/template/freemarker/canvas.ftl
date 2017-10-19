<script src="/js/api.js"></script>
<script src="/js/canvas-all.js"></script>
<script src="/js/chatter-talk.js"></script>
<script src="/js/json2.js"></script>
<script src="/js/publisher.js"></script>

<div class="canvasContainer">
    signedRequest <textarea id="signedRequest" rows="5" cols="100"> ${escapedSignedRequest} </textarea> <br/><br/><hr/><br/>
    <input id="canvasPublishMessage" value=""/> <button onclick="canvasPublish( document.getElementById('canvasPublishMessage').value )" > Publish </button> <br/><br/><hr/><br/>
    Subcribed Message <br/> <br/>  <div id="canvasSubscribed"/> <br/><br/><hr/><br/>
    Subcribed Resize Event <br/> <br/>  <div id="canvasResizeSubscribed"/> <br/><br/><hr/><br/>
    Self Resize Servicve <button onclick="showCurrentSize()">Show Current Size</button> <br/>
    <input id="resizeWidthPx" value="" placeholder="width px"/> &nbsp; &nbsp; <input id="resizeHeightPx" value="" placeholder="height px"/> <button onclick="resizeMe( document.getElementById('resizeWidthPx').value, document.getElementById('resizeHeightPx').value )" > Resize Me </button> <br/><br/><hr/><br/>
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
        console.log(' canvas published : ' + message + ' to ' + canvasTopic );
    }
</script>

<!-- subscribe block -->
<script>
    Sfdc.canvas.client.subscribe(signedRequest.client, [{
        name: namespacePrefix  + vfTopic,
        onData: onData
    }]);

    function onData(message) {
        console.log(' canvas received message from visualforce ' + message.message );
        document.getElementById('canvasSubscribed').innerHTML = message.message;
    }
</script>


<!-- resize block -->
<script>
    function showCurrentSize() {
        var sizes = Sfdc.canvas.client.size();
        document.getElementById('resizeWidthPx').value = sizes.widths.pageWidth;
        document.getElementById('resizeHeightPx').value = sizes.heights.pageHeight;
    }

    Sfdc.canvas.client.subscribe(signedRequest.client, [{
        name: 'canvas.resize',          /** there is also, sfdc.streaming **/
        onData: onResizeData
    }]);

    function onResizeData(message) {
        console.log(' canvas received resize event ');
        console.log(message);
        document.getElementById('canvasResizeSubscribed').innerHTML = JSON.stringify(message);
    }

    function resizeMe(width, height) {
        console.log(' canvas is trying to resize it self to width ' + width + ' height ' + height );
        var dimension = Sfdc.canvas.client.size(signedRequest.client);
        Sfdc.canvas.client.resize(signedRequest.client, {height : height + "px", width : width + "px" } );
    }
</script>

<!-- api block -->


<!-- chatter block -->