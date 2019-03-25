package com.gb.vibrator.util;

import com.gb.vibrator.domain.DaoMaster;
import com.gb.vibrator.domain.DaoSession;
import com.gb.vibrator.domain.VibratorData;
import com.gb.vibrator.domain.VibratorDataDao;
import java.util.List;

/**
 * Create by wgb on 2019/3/25.
 */
public class DaoUtils {
  private static final String DATABASE_NAME = "vibrator_data.db";
  private static DaoSession mDaoSession;

  public static DaoSession getDaoSession() {
    if (mDaoSession == null) {
      DaoMaster.OpenHelper openHelper =
          new DaoMaster.DevOpenHelper( MyApplication.getContext(), DATABASE_NAME, null );
      DaoMaster daoMaster = new DaoMaster( openHelper.getWritableDatabase() );
      mDaoSession = daoMaster.newSession();
    }
    return mDaoSession;
  }

  private static VibratorDataDao getVibratorDataDao() {
    return getDaoSession().getVibratorDataDao();
  }

  //增
  public static void insertData(VibratorData vibratorData) {
    getVibratorDataDao().insert( vibratorData );
  }

  //删
  public static void deleteById(long id) {
    getVibratorDataDao().deleteByKey( id );
  }

  //改
  public static void updateData(long id, String title, String data, boolean repeat) {
    VibratorDataDao vibratorDataDao = getVibratorDataDao();
    VibratorData vibratorData = vibratorDataDao.queryBuilder()
        .where( VibratorDataDao.Properties.Id.eq( id ) )
        .build()
        .unique();
    vibratorData.setTitle( title );
    vibratorData.setData( data );
    vibratorData.setRepeat( repeat );
    vibratorDataDao.update( vibratorData );
  }

  //查
  public static List<VibratorData> getAllData() {
    return getVibratorDataDao().queryBuilder()
        .orderAsc( VibratorDataDao.Properties.Id )
        .build()
        .list();
  }
}
