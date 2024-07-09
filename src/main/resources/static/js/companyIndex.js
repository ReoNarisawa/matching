'use strict'
function showUserModal(element) {
	const userCard = element.closest('.user-card');
	const userId = userCard.getAttribute('data-user-id');
	const userName = userCard.querySelector('h3').textContent;
	const userAddress = userCard.querySelector('p').textContent;

	document.getElementById('modalUserId').value = userId;
	document.getElementById('modalUserName').textContent = userName;
	document.getElementById('modalUserAddress').textContent = userAddress;

	const modal = document.getElementById('userModal');
	modal.style.display = "block";
}

function closeModal() {
	const modal = document.getElementById('userModal');
	modal.style.display = "none";
}

window.onclick = function(event) {
	const modal = document.getElementById('userModal');
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

function startChat() {
	const userId = document.getElementById('modalUserId').value;
	console.log("Starting chat with user ID:", userId);
	fetch(`/getChatGroupId?userId=${userId}`)
		.then(response => response.json())
		.then(data => {
			if (data && data.chatGroupId && data.chatGroupId !== -1) {
				window.location.href = `/chat?chatGroupId=${data.chatGroupId}`;
			} else {
				console.error('Error: chatGroupId not found');
			}
		})
		.catch(error => console.error('Error fetching chatGroupId:', error));
}

// 並べ替えボタン開く時
function showSortOptions() {
	var sortOptions = document.getElementById('sortOptions');
	if (sortOptions.style.display === 'none' || sortOptions.style.display === '') {
		sortOptions.style.display = 'block';
	} else {
		sortOptions.style.display = 'none';
	}
}