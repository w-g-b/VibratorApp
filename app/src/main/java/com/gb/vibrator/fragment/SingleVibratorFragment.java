package com.gb.vibrator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
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
public class SingleVibratorFragment extends Fragment {
  Vibrator vibrator;
  @BindView(R.id.vibrator_time_edt) EditText vibratorTimeEdt;
  @BindView(R.id.vibrator_amplitude_edt) EditText vibratorAmplitudeEdt;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate( R.layout.fragment_single_vibrator, container, false );
    ButterKnife.bind( this, view );
    init();
    return view;
  }

  private void init() {
    vibrator = (Vibrator) getActivity().getSystemService( Context.VIBRATOR_SERVICE );
    vibratorTimeEdt.setText( "100" );
    vibratorAmplitudeEdt.setText( "" + VibrationEffect.DEFAULT_AMPLITUDE );
  }

  @OnClick(R.id.start_btn) public void startVibrator() {
    int amplitude = Integer.parseInt( vibratorAmplitudeEdt.getText().toString() );
    if (amplitude != -1 && (amplitude < 1 || amplitude > 255)) {
      Toast.makeText( getActivity(), "振动幅度数据错误，请输入合适的数据", Toast.LENGTH_SHORT ).show();
      return;
    }
    VibrationEffect vibrationEffect =
        VibrationEffect.createOneShot( Integer.parseInt( vibratorTimeEdt.getText().toString() ),
            amplitude );
    vibrator.vibrate( vibrationEffect );
  }

  @OnClick(R.id.stop_btn) public void stopVibrator() {
    vibrator.cancel();
  }
}
