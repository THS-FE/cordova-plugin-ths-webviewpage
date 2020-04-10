var exec = require('cordova/exec');

exports.startWebViewPage = function (url, success, error) {
    exec(success, error, 'thswebviewpage', 'startWebViewPage', [url]);
};
