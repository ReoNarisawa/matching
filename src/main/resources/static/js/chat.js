var stompClient = null;

function connect() {
	var socket = new SockJS('/Matching/chat-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/public', function(messageOutput) {
			console.log('Received: ' + messageOutput.body);
			showMessage(JSON.parse(messageOutput.body));
		});
		scrollToBottom(); // 初回接続時にスクロールを一番下に
	});
}

function sendMessage() {
    var messageInput = document.getElementById('messageInput').value;
    var chatGroupId = document.getElementById('chatGroupId').value;
    var sender = document.getElementById('userEmail').textContent;

    var message = {
        content: messageInput,
        timestamp: new Date().toISOString(),
        chatGroupId: chatGroupId,
        sender: sender
    };
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(message));
    document.getElementById('messageInput').value = '';
}

function showMessage(message) {
    var messageElement = document.createElement('div');
    messageElement.classList.add('chat-message');
    messageElement.textContent = message.content + ' | ' + message.timestamp;
    document.getElementById('chatMessages').appendChild(messageElement);
    scrollToBottom(); // メッセージ追加後にスクロールを一番下に
}

function scrollToBottom() {
	var chatMessages = document.getElementById('chatMessages');
	chatMessages.scrollTop = chatMessages.scrollHeight;
}

connect();