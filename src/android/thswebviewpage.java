package cn.com.ths.webview.page;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cn.com.ths.thswebview.ThsWebViewActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import cn.com.ths.webview.page.AESUtils;

/**
 * This class echoes a string called from JavaScript.
 */
public class thswebviewpage extends CordovaPlugin {
    private String ivStr = "solutionsolution"; //加密解密偏移量
  /**
   * 权限列表
   */
  private String[] locPerArr = new String[] {
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.READ_EXTERNAL_STORAGE
  };

  /**
   * 初始化插件
   *
   * @param cordova
   * @param webView
   */
  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    promtForLocation();
  }

  /**
   * 申请权限
   */
  private void promtForLocation() {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      for (int i = 0, len = locPerArr.length; i < len; i++) {
        if (!PermissionHelper.hasPermission(this, locPerArr[i])) {
          PermissionHelper.requestPermission(this, i, locPerArr[i]);
          return;
        }
      }
    }

  }

  @Override
  public void onRequestPermissionResult(int requestCode,
                                        String[] permissions, int[] grantResults) throws JSONException {
    // TODO Auto-generated method stub
    for (int r : grantResults) {
      if (r == PackageManager.PERMISSION_DENIED) {
        return;
      }
    }
    promtForLocation();
  }

  @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("startWebViewPage")) {
            String url = args.getString(0);
            String title = args.getString(1);
            this.startWebViewPage(url,title, callbackContext);
            return true;
        }else if(action.equals("encryptStr")){
          // 待加密的字符串
          String encryptStr = args.getString(0);
          String keyStr = args.getString(1);
          String content = null;
          try {
            content = AESUtils.aesEncrypt(encryptStr,keyStr, ivStr);
          } catch (Exception e) {
            e.printStackTrace();
          }
          callbackContext.success(content);
          return true;
        }
        return false;
    }

    private void startWebViewPage(String url,String title, CallbackContext callbackContext) {
        if (url != null && url.length() > 0) {
            Intent  intent = new Intent(cordova.getActivity(), ThsWebViewActivity.class);
            intent.putExtra(ThsWebViewActivity.EXTRA_STR,url);
            // cordova.getActivity().finish();
            intent.putExtra(ThsWebViewActivity.EXTRA_TITLE,title);
            cordova.getActivity().startActivity(intent);
            callbackContext.success("success");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
