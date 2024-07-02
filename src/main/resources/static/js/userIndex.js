function searchCompanies() {
    alert("検索機能は現在実装されていません");
}

function showCompanyModal(element) {
    const companyCard = element.closest('.company-card');
    const companyName = companyCard.querySelector('h3').textContent;
    const companyAddress = companyCard.querySelector('p').textContent;

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