package cn.com.ths.webview.page;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cn.com.ths.thswebview.ThsWebViewActivity;
import android.content.Intent;

/**
 * This class echoes a string called from JavaScript.
 */
public class thswebviewpage extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("startWebViewPage")) {
            String url = args.getString(0);
            this.startWebViewPage(url, callbackContext);
            return true;
        }
        return false;
    }

    private void startWebViewPage(String url, CallbackContext callbackContext) {
        if (url != null && url.length() > 0) {
            Intent  intent = new Intent(cordova.getActivity(), ThsWebViewActivity.class);
            intent.putExtra(ThsWebViewActivity.EXTRA_STR,url);
            cordova.getActivity().startActivity(intent);
            callbackContext.success("success");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
