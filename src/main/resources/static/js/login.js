/**
 * addEventListenerメソッド
 * 第一引数にはイベントの種類を指定。ここでは "DOMContentLoaded" を使用
 * 第二引数にはイベントが発生したときに実行される関数を指定
 */


/**
 * DOMContentLoaded
 * 初期のHTML文書が完全に読み込まれたときに発生します。
 * つまり、画像やスタイルシートなどの外部リソースが完全に読み込まれる前に発生
 * これにより、ページの解析が完了した時点でJavaScriptコードを実行できる
 */

/**
 * function() {...}
 * 無名関数（匿名関数とも言う）と呼ばれます。
 * 特定の名前が付けられていない関数で、指定したイベントが発生したときに実行される。
 */

document.addEventListener("DOMContentLoaded", function() {
	if (message) {
		alert(message);
		}
	if (error) {
        alert("メールアドレスとパスワードの組み合わせが間違っています");
    }
    if (logoutMessage) {
        alert(logoutMessage);
    }
});