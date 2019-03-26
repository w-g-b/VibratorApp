package com.gb.vibrator.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
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

  public void updateUIData(VibratorData data) {
    titleEtx.setText( data.getTitle() );
    vibratorTa.setText( data.getData() );
    repeatCb.setChecked( data.getRepeat() );
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
    if (!StringUtils.isNumOrSpace( waveStr )) {
      Toast.makeText( getContext(), "输入格式有误，请检查", Toast.LENGTH_SHORT ).show();
      return;
    }

    long[] data = StringUtils.spaceStrToLongArray( waveStr );
    int repeat = repeatCb.isChecked() ? 0 : -1;
    VibrationEffect vibrationEffect = VibrationEffect.createWaveform( data, repeat );
    vibrator.vibrate( vibrationEffect );
  }

  @OnClick(R.id.save_btn) public void saveBtn() {
    String waveStr = vibratorTa.getText().toString();
    if (!StringUtils.isNumOrSpace( waveStr )) {
      Toast.makeText( getContext(), "输入格式有误，请检查", Toast.LENGTH_SHORT ).show();
      return;
    }
    waveStr = StringUtils.formatStr( waveStr );
    VibratorData vibratorData = new VibratorData();
    String title = titleEtx.getText().toString();
    if (StringUtils.isEmpty( title )) {
      DateFormat df = new SimpleDateFormat( "MM-dd HH:mm:ss" );
      title = df.format( new Date() );
    }
    vibratorData.setTitle( title );
    vibratorData.setData( waveStr );
    vibratorData.setRepeat( repeatCb.isChecked() );
    mListener.onClick( vibratorData );
    dismiss();
  }

  @OnClick(R.id.stop_vibrator_btn) public void stopVibrator() {
    vibrator.cancel();
  }

  @OnClick(R.id.format_btn) public void formatEdt() {
    String str = vibratorTa.getText().toString();
    if (StringUtils.isNumOrSpace( str )) {
      str = StringUtils.formatStr( str );
      vibratorTa.setText( str );
      vibratorTa.setSelection( str.length() );
    } else {
      Toast.makeText( getContext(), "数据有误，无法格式化", Toast.LENGTH_SHORT ).show();
    }
  }

  public interface OnSaveListener {
    void onClick(VibratorData data);
  }
}
