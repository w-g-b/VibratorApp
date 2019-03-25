package com.gb.vibrator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gb.vibrator.R;

/**
 * Create by wgb on 2019/3/24.
 */
public class WaveVibratorFragment extends Fragment {
  Vibrator vibrator;
  @BindView(R.id.wave_vibrator_edt) EditText waveVibratorEdt;

  @BindView(R.id.checkBox) CheckBox checkBox;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate( R.layout.fragment_wave_vibrator, container, false );
    ButterKnife.bind( this, view );
    init();
    return view;
  }

  private void init() {
    vibrator = (Vibrator) getActivity().getSystemService( Context.VIBRATOR_SERVICE );
    waveVibratorEdt.setText( "1000 150 300 200" );
  }

  @OnClick(R.id.start_wave_btn) public void startWaveVibrator() {
    String waveStr = waveVibratorEdt.getText().toString();
    String[] dataStr = waveStr.split( " " );
    long[] data = new long[dataStr.length];
    for (int i = 0; i < dataStr.length; i++) {
      data[i] = Long.parseLong( dataStr[i] );
    }
    int repeat = checkBox.isChecked() ? 0 : -1;
    VibrationEffect vibrationEffect = VibrationEffect.createWaveform( data, repeat );
    vibrator.vibrate( vibrationEffect );
  }

  @OnClick(R.id.stop_wave_btn) public void stopWaveVibrator() {
    vibrator.cancel();
  }
}
