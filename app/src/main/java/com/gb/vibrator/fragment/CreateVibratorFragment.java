package com.gb.vibrator.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gb.vibrator.R;
import com.gb.vibrator.adapter.VibratorDataAdapter;
import com.gb.vibrator.dialog.VibratorEditDialog;
import com.gb.vibrator.domain.VibratorData;
import com.gb.vibrator.util.DaoUtils;
import java.util.List;

/**
 * Create by wgb on 2019/3/24.
 */
public class CreateVibratorFragment extends Fragment {
  @BindView(R.id.recycleview) RecyclerView recyclerView;
  List<VibratorData> mList;
  VibratorDataAdapter mAdapter;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate( R.layout.fragment_create_vibrator, container, false );
    ButterKnife.bind( this, view );
    init();
    return view;
  }

  private void init() {
    mList = DaoUtils.getAllData();
    if (mList == null || mList.size() == 0) {
      addData();
    }
    recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
    mAdapter = new VibratorDataAdapter( getActivity(), mList );
    mAdapter.setOnItemClickListener( new VibratorDataAdapter.OnItemClickListener() {
      @Override public void onItemClick(View view, int position) {
        VibratorEditDialog dialog = new VibratorEditDialog( getActivity(), (VibratorData data) -> {
          //添加数据
          data.setId( mList.get( position ).getId() );
          DaoUtils.updateData( data );
          mAdapter.updateData( position, data );
        } );
        dialog.show();
        dialog.updateUIData( mList.get( position ) );
      }
    } );
    mAdapter.setOnLongClickListener(
        (view, position) -> new AlertDialog.Builder( getContext() ).setTitle( "删除该数据？" )
            .setCancelable( true )
            .setPositiveButton( "确定", (dialog, which) -> {
              DaoUtils.deleteById( mList.get( position ).getId() );
              mAdapter.removeData( position );
            } )
            .setNegativeButton( "取消", (dialog, which) -> dialog.dismiss() )
            .create()
            .show() );
    recyclerView.setAdapter( mAdapter );
  }

  private void addData() {
    VibratorData data1 = new VibratorData();
    data1.setTitle( "微信振动" );
    data1.setData( "1000 160 300 200" );
    data1.setRepeat( false );
    VibratorData data2 = new VibratorData();
    data2.setTitle( "节奏振动" );
    data2.setData( "1000 220 180 220 180 140" );
    data2.setRepeat( true );
    DaoUtils.insertData( data1 );
    DaoUtils.insertData( data2 );
    mList.add( data1 );
    mList.add( data2 );
  }

  @OnClick(R.id.create_vibrator_btn) public void createVibratorEditDialog() {
    VibratorEditDialog dialog = new VibratorEditDialog( getActivity(), (VibratorData data) -> {
      //添加数据
      DaoUtils.insertData( data );
      mAdapter.addData( mList.size(), data );
    } );
    dialog.show();
    //VibratorData data = new VibratorData();
    //data.setTitle( "微信振动" );
    //data.updateUIData( "1000 160 300 200" );
    //data.setRepeat( false );
    //data.setTitle( "节奏振动" );
    //data.updateUIData( "1000 220 180 220 180 140" );
    //data.setRepeat( true );
  }
}
