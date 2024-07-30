'use strict'
function showCompanyModal(element) {
	const companyCard = element.closest('.company-card');
	const companyId = companyCard.getAttribute('data-company-id');
	const companyName = companyCard.querySelector('h3').textContent;
	const companyAddress = companyCard.querySelector('p').textContent;

	document.getElementById('modalCompanyId').value = companyId;
	document.getElementById('modalCompanyName').textContent = companyName;
	document.getElementById('modalCompanyAddress').textContent = companyAddress;

	const modal = document.getElementById('companyModal');
	modal.style.display = "block";
}

function closeModal() {
	const modal = document.getElementById('companyModal');
	modal.style.display = "none";
}

window.onclick = function(event) {
	const modal = document.getElementById('companyModal');
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

function startChat() {
	const companyId = document.getElementById('modalCompanyId').value;
	console.log("Starting chat with company ID:", companyId);
	fetch(`/Matching/getChatGroupId?companyId=${companyId}`)
		.then(response => response.json())
		.then(data => {
			if (data && data.chatGroupId && data.chatGroupId !== -1) {
				window.location.href = `/Matching/chat?chatGroupId=${data.chatGroupId}`;
			} else {
				console.error('Error: chatGroupId not found');
			}
		})
		.catch(error => console.error('Error fetching chatGroupId:', error));
}

// 並べ替えボタン開く時
function showSortOptions() {
	var sortOptions = document.getElementById('sortOptions');
	if (sortOptions.style.display === 'none') {
		sortOptions.style.display = 'block';
	} else {
		sortOptions.style.display = 'none';
	}
}