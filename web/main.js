var mywebworker;

window.onload = function() {
  mywebworker = new Worker('gwt/mywebworker/mywebworker.nocache.js');
  mywebworker.addEventListener('message', function (e) {
    console.log('Worker said: ', e.data);
    var d = document.createElement('div');
    d.innerText = 'Worker generated: ' + e.data.payload;
    document.body.appendChild(d);
  }, false);

}

function callWebWorker() {
  console.log("Calling web worker...");
  mywebworker.postMessage('whatever');
}
