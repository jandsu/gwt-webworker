var mywebworker;

window.onload = function() {
  mywebworker = new Worker('gwt/mywebworker/mywebworker.nocache.js');
  mywebworker.addEventListener('message', function (e) {
    console.log('Worker said: ', e.data);
  }, false);

}

function callWebWorker() {
  console.log("Calling web worker...");
  mywebworker.postMessage('whatever');
}
