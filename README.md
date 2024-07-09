# マッチングアプリ　ポートフォリオ
  企業・福祉人材のマッチングアプリです。<br>
  前職が医療職であり、慢性的な人材不足を解決したいと考え作成しました。<br>
<br>


## 機能一覧
### アカウント登録機能
![createuser](https://github.com/ReoNarisawa/matching/assets/128871965/406b2e48-d3c0-4fcd-8f2f-21f3c28802e3)
login画面のcreate an accountより、アカウント登録を行います。<br>
UserかCompanyを選択します。<br>
登録したデータは、login_dataテーブルと、roleに基づきusersテーブルか、companiesテーブルに登録されます。<br>
同じメールアドレスのアカウントは登録できないようになっています。<br>


### ユーザー登録時バリデーション機能
![registerValidate](https://github.com/ReoNarisawa/matching/assets/128871965/cad7478b-d1bc-4412-b315-5384e33baeeb)<br>
NotEmptyバリデーション機能があります。<br>


### ログイン・ログアウト機能
![login_logout_loginValidate](https://github.com/ReoNarisawa/matching/assets/128871965/60aaeeb3-c557-438c-b71f-1e730d61445c)<br>
作成したアカウントのroleに基づき、userIndex.htmlか、companyIndex.htmlを開きます。<br>
ログイン後、右上のログアウトボタンよりログアウト出来ます。<br>
ログイン画面でも、NotEmptyバリデーション機能があります。<br>
メールアドレスとパスワードの組み合わせが間違っているとメッセージが表示されます。<br>


### サーチ・ソート機能
![search_sort](https://github.com/ReoNarisawa/matching/assets/128871965/c137defd-6b1c-4e55-a2ba-094735221f77)<br>
検索窓から、名前で企業やユーザーを検索できます。<br>
空白の状態で検索ボタンをクリックすると、全企業（ユーザー）が表示されます。<br>
並べ替えボタンで、名前順・住所順でソート出来ます。<br>


### リアルタイムチャット機能
![realtimechat](https://github.com/ReoNarisawa/matching/assets/128871965/693ad98a-56f2-4925-adb6-691ba314d794)<br>
各Index画面より、ユーザーや企業をクリックすると、モーダル画面が開きます。<br>
モーダル画面右下のChatボタンをクリックすると、一意のチャット画面が生成されます。<br>
相手がChat画面を開いていなくても、相手方My PageのChat Listに、一意のチャット画面へのリンクが生成されます。<br>
（自分のMy PageのChat Listにもリンクが生成されます。）<br>
Web Socketを使用しており、リアルタイムでのチャットが可能です。<br>
送信したメッセージはデータベースに保存されているため、チャット画面を閉じても、メッセージが消えることはありません。<br>
<br>


## 使用技術
Frontend：html,css,JavaScript<br>
Backend：Java,JavaScript<br>
Database：MySQL<br>
FreamWork：Spring Boot, security<br>
-WebSocket<br>
<br>

## データベース作成クエリ
CREATE TABLE chat_group (<br>
    id INT NOT NULL AUTO_INCREMENT,<br>
    user_id INT,<br>
    company_id INT,<br>
    PRIMARY KEY (id),<br>
    INDEX user_id_idx (user_id),<br>
    INDEX company_id_idx (company_id)<br>
);<br>
<br>
CREATE TABLE chat_message (<br>
    id BIGINT NOT NULL AUTO_INCREMENT,<br>
    content VARCHAR(255),<br>
    timestamp VARCHAR(255) NOT NULL,<br>
    chat_groupid INT,<br>
    recipient VARCHAR(255),<br>
    sender VARCHAR(255),<br>
    PRIMARY KEY (id),<br>
    INDEX chat_groupid_idx (chat_groupid)<br>
);<br>
<br>
CREATE TABLE companies (<br>
    id INT NOT NULL AUTO_INCREMENT,<br>
    companyname VARCHAR(255),<br>
    tel VARCHAR(255),<br>
    email VARCHAR(255),<br>
    address VARCHAR(255),<br>
    facilitytype VARCHAR(255),<br>
    company_registered DATETIME(6),<br>
    PRIMARY KEY (id)<br>
);<br>
<br>
CREATE TABLE login_data (<br>
    id INT NOT NULL AUTO_INCREMENT,<br>
    username VARCHAR(255) NOT NULL,<br>
    password VARCHAR(255) NOT NULL,<br>
    email VARCHAR(255) NOT NULL,<br>
    role INT,<br>
    PRIMARY KEY (id)<br>
);<br>
<br>
CREATE TABLE role (<br>
    id INT NOT NULL AUTO_INCREMENT,<br>
    name VARCHAR(32),<br>
    PRIMARY KEY (id)<br>
);<br>
<br>
CREATE TABLE users (<br>
    id INT NOT NULL AUTO_INCREMENT,<br>
    last_name VARCHAR(255),<br>
    first_name VARCHAR(255),<br>
    birth DATETIME(6),<br>
    sex VARCHAR(255),<br>
    tel VARCHAR(255),<br>
    email VARCHAR(255),<br>
    address VARCHAR(255),<br>
    jobtype VARCHAR(255),<br>
    user_registered DATETIME(6),<br>
    PRIMARY KEY (id)<br>
);<br>
<br>
<br>
## 今後の課題
・アカウント消去機能<br>
・ユーザーや企業の画像を編集可能に<br>
・チャット画面のレイアウト<br>
