var exec = require('cordova/exec');

exports.coolMethod = function (url, success, error) {
    exec(success, error, 'thswebviewpage', 'startWebViewPage', [url]);
};
