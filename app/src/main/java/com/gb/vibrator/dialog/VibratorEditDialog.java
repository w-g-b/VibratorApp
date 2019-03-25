package com.gb.vibrator.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gb.vibrator.R;
import com.gb.vibrator.domain.VibratorData;
import com.gb.vibrator.util.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by wgb on 2019/3/25.
 */
public class VibratorEditDialog extends AppCompatDialog {

  @BindView(R.id.title_etx) EditText titleEtx;
  @BindView(R.id.vibrator_ta) EditText vibratorTa;
  @BindView(R.id.repeat_cb) CheckBox repeatCb;

  Context mContext;
  Vibrator vibrator;
  OnSaveListener mListener;

  public VibratorEditDialog(Context context, OnSaveListener listener) {
    //super( context );
    super( context, R.style.VibratorDialog );
    mContext = context;
    mListener = listener;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate( savedInstanceState );
    requestWindowFeature( Window.FEATURE_NO_TITLE );
    setContentView( R.layout.dialog_vibrator_edit );
    ButterKnife.bind( this );
    init();
  }

  private void init() {
    vibrator = (Vibrator) mContext.getSystemService( Context.VIBRATOR_SERVICE );
  }

  @OnClick(R.id.close_btn) public void closeDialog() {
    dismiss();
  }

  @OnClick(R.id.test_vibrator_btn) public void testVibratorBtn() {
    String waveStr = vibratorTa.getText().toString();
    String[] dataStr = waveStr.split( " " );
    long[] data = new long[dataStr.length];
    for (int i = 0; i < dataStr.length; i++) {
      data[i] = Long.parseLong( dataStr[i] );
    }
    int repeat = repeatCb.isChecked() ? 0 : -1;
    VibrationEffect vibrationEffect = VibrationEffect.createWaveform( data, repeat );
    vibrator.vibrate( vibrationEffect );
  }

  @OnClick(R.id.save_btn) public void saveBtn() {
    VibratorData vibratorData = new VibratorData();
    String title = titleEtx.getText().toString();
    if (StringUtils.isEmpty( title )) {
      DateFormat df = new SimpleDateFormat( "MM-dd HH:mm:ss" );
      title = df.format( new Date() );
    }
    vibratorData.setTitle( title );
    vibratorData.setData( vibratorTa.getText().toString() );
    vibratorData.setRepeat( repeatCb.isChecked() );
    mListener.onClick( vibratorData );
    dismiss();
  }

  @OnClick(R.id.stop_vibrator_btn) public void stopVibrator() {
    vibrator.cancel();
  }

  public interface OnSaveListener {
    void onClick(VibratorData data);
  }
}
