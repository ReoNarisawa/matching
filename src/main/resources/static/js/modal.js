// モーダルの要素を取得
var modal = document.getElementById("editModal");
var btn = document.getElementById("editButton");
var span = document.getElementsByClassName("close")[0];

// ボタンがクリックされたときにモーダルを開く
btn.onclick = function() {
    modal.style.display = "block";
}

// スパン (x) がクリックされたときにモーダルを閉じる
span.onclick = function() {
    modal.style.display = "none";
}

// モーダルの外をクリックされたときにモーダルを閉じる
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}