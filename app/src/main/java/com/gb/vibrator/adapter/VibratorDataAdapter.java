package com.gb.vibrator.adapter;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gb.vibrator.R;
import com.gb.vibrator.domain.VibratorData;
import java.util.List;

/**
 * Create by wgb on 2019/3/25.
 */
public class VibratorDataAdapter extends RecyclerView.Adapter<VibratorDataAdapter.ViewHolder> {
  private List<VibratorData> mVibratorDataList;
  private Context mContext;

  private Vibrator mVibrator;

  public VibratorDataAdapter(Context context, List<VibratorData> vibratorDataList) {
    mContext = context;
    mVibratorDataList = vibratorDataList;
    mVibrator = (Vibrator) mContext.getSystemService( Context.VIBRATOR_SERVICE );
  }

  //添加数据
  public void addData(int position, VibratorData data) {
    mVibratorDataList.add( position, data );
    notifyItemInserted( position );
  }

  //删除数据
  public void removeData(int position) {
    mVibratorDataList.remove( position );
    notifyItemRemoved( position );
    notifyDataSetChanged();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.serial_number_tx) TextView serialNumberTx;
    @BindView(R.id.title_tx) TextView title;
    @BindView(R.id.start_btn) Button startBtn;
    @BindView(R.id.stop_btn) Button stopBtn;

    public ViewHolder(@NonNull View itemView) {
      super( itemView );
      ButterKnife.bind( this, itemView );
    }
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from( parent.getContext() )
        .inflate( R.layout.recycle_item_vibrator, parent, false );
    ViewHolder holder = new ViewHolder( view );
    return holder;
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    VibratorData vibratorData = mVibratorDataList.get( position );
    holder.serialNumberTx.setText( position + 1 + ". " );
    holder.title.setText( vibratorData.getTitle() );
    holder.startBtn.setOnClickListener( (View view) -> {
      String[] dataStr = vibratorData.getData().split( " " );
      long[] data = new long[dataStr.length];
      for (int i = 0; i < dataStr.length; i++) {
        data[i] = Long.parseLong( dataStr[i] );
      }
      int repeat = vibratorData.getRepeat() ? 0 : -1;
      VibrationEffect vibrationEffect = VibrationEffect.createWaveform( data, repeat );
      mVibrator.vibrate( vibrationEffect );
    } );
    holder.stopBtn.setOnClickListener( (View v) -> {
      mVibrator.cancel();
    } );
  }

  @Override public int getItemCount() {
    return mVibratorDataList.size();
  }
}
