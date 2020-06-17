var exec = require('cordova/exec');

/**
 * 打开webView
 * @param {String} url 要打开的web 页面地址
 * @param {*} title 标题展示
 * @param {*} success 成功回调
 * @param {*} error 失败回调
 */
exports.startWebViewPage = function (url,title, success, error) {
    exec(success, error, 'thswebviewpage', 'startWebViewPage', [url,title]);
};
