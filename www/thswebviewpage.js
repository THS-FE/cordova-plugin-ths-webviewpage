var exec = require('cordova/exec');

/**
 * 打开webView
 * @param {String} url 要打开的web 页面地址
 * @param {*} title 标题展示
 * @param {*} success 成功回调
 * @param {*} error 失败回调
 */
exports.startWebViewPage = function (url, title, success, error) {
    exec(success, error, 'thswebviewpage', 'startWebViewPage', [url, title]);
};

/**
 * 加密文本
 * @param {string} content 文本内容
 * @param {string} keyStr 密钥
 * @param {string} success 成功
 * @param {string} error 失败
 */
exports.encryptStr = function (content, keyStr, success, error) {
    exec(success, error, 'thswebviewpage', 'encryptStr', [content, keyStr]);
};
