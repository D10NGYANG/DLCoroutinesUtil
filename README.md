# DLCoroutinesUtil
协程封装工具[![](https://jitpack.io/v/D10NGYANG/DLCoroutinesUtil.svg)](https://jitpack.io/#D10NGYANG/DLCoroutinesUtil)

## 使用
1 Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2 Add the dependency
```gradle
dependencies {
    // 协程封装工具
    implementation 'com.github.D10NGYANG:DLCoroutinesUtil:0.1'
    // 协程
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
}
```
3 混淆
```properties
-keep class com.d10ng.coroutines.** {*;}
-dontwarn com.d10ng.coroutines.**
```
