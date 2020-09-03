# cordova-plugin-ths-webviewpage

自定义webView，运行在独立进程中。

依赖aar包源码地址：http://x.x.x.x:xxxx/svn/Products/环保移动平台/工程/代码/移动门户/ThsWebView

## 支持平台

Android

## 安装插件

```
# 通过npm 安装插件
cordova plugin add cordova-plugin-ths-webviewpage
# 通过github安装
cordova plugin add https://github.com/THS-FE/cordova-plugin-ths-webviewpage
# 通过本地文件路径安装
cordova plugin add 文件路径
```

**说明： ionic 项目命令前加上ionic，即ionic cordova plugin xxxxx**

## 使用方法

启动网页页面

```java
cordova.plugin.thswebviewpage.startWebViewPage(url,title,(success) => {
      console.log(success);
    }, (error) => {
      console.log(error);
});
```

**说明：使用ts 进行开发时，需要在文件上变声明下declare const cordova，不然会报错;**

```typescript
import { Component, OnInit, Input } from '@angular/core';
import { WebIntent } from '@ionic-native/web-intent/ngx';
declare let cordova;
@Component({
  selector: 'app-explore-container',
  templateUrl: './explore-container.component.html',
  styleUrls: ['./explore-container.component.scss'],
})
```

## 常见错误
