# android-repo-af

[![CircleCI](https://circleci.com/gh/amarillons/android-repo-af/tree/develop.svg?style=svg)](https://circleci.com/gh/amarillons/android-repo-af/tree/develop)

Android 用の公開リポジトリ

### ビルド方法
- gradle
- CircleCI

### 使用ライブラリ
- OkHttp (https://square.github.io/okhttp/ ) インターネット接続
- Picasso (https://square.github.io/picasso/ )　画像データ表示


### アプリの動作
- サーバーから「三十六歌仙の画像と解説」を取得して、表示する。
- 表示部分のコード（ListView を使う）: [KasenActivity.kt](https://github.com/amarillons/android-repo-af/blob/develop/app/src/main/java/jp/afb114714/android/app/KasenActivity.kt)

<img src="https://user-images.githubusercontent.com/2384963/85187913-58c4d580-b2de-11ea-8154-9d773130f7b5.png" width="350">
