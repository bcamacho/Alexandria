package it.jaschke.alexandria.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import it.jaschke.alexandria.AddBook;


public class GetBarcode extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    IntentIntegrator integrator = new IntentIntegrator(this); // `this` is the current Activity
    integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
    integrator.setPrompt("Scan a barcode");
    integrator.setCameraId(0);  // Use a specific camera of the device
    integrator.setBeepEnabled(false);
    integrator.initiateScan();

  }
  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    if (scanResult != null) {
      // handle scan result
      try {
        Log.d(this.getClass().getSimpleName(), scanResult.getContents());
        AddBook.setBarCodeData(scanResult.getContents());
      }catch (Exception e){
        Log.e(getClass().getSimpleName(), String.valueOf(e));
        AddBook.setBarCodeData("");
      }
    } else {
      // else continue with any other code you need in the method
      Log.e(getClass().getSimpleName(), "ERROR");
      AddBook.setBarCodeData("");

    }
    finish();

  }
}
