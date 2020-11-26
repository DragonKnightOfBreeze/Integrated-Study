let websocket = null
if('WebSocket' in window) {
  websocket = new WebSocket("ws://localhost:8080/message/windea")
} else if('MozWebSocket' in window) {
  websocket = new MozWebSocket("ws://localhost:8080/message/windea")
} else {
  websocket = new SockJS("localhost:8088/message/windea")
}

//连接发生错误的回调方法
websocket.onerror = function() {
  showMessage("WebSocket连接发生错误")
}

//连接成功建立的回调方法
websocket.onopen = function() {
  showMessage("WebSocket连接成功")
}

//接收到消息的回调方法
websocket.onmessage = function(event) {
  showMessage(event.data)
}

//连接关闭的回调方法
websocket.onclose = function() {
  showMessage("WebSocket连接关闭")
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function() {
  closeWebSocket()
}

//将消息显示在网页上
function showMessage(innerHTML) {
  $("#message").html(innerHTML)
}

//关闭WebSocket连接
function closeWebSocket() {
  websocket.close()
}

//发送消息
function send() {
  const message = $("#text").value
  websocket.send(message)
}
