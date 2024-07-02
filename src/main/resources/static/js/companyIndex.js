function searchUsers() {
    alert("検索機能は現在実装されていません");
}

function showUserModal(element) {
    const userCard = element.closest('.user-card');
    const userName = userCard.querySelector('h3').textContent;
    const userAddress = userCard.querySelector('p').textContent;

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