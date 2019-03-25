package com.gb.vibrator.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create by wgb on 2019/3/25.
 */
@Entity public class VibratorData {
  @Id(autoincrement = true) private Long id;
  private String title;
  private String data;
  private boolean repeat;
  @Generated(hash = 1614026602)
  public VibratorData(Long id, String title, String data, boolean repeat) {
      this.id = id;
      this.title = title;
      this.data = data;
      this.repeat = repeat;
  }
  @Generated(hash = 699241440)
  public VibratorData() {
  }
  public Long getId() {
      return this.id;
  }
  public void setId(Long id) {
      this.id = id;
  }
  public String getTitle() {
      return this.title;
  }
  public void setTitle(String title) {
      this.title = title;
  }
  public String getData() {
      return this.data;
  }
  public void setData(String data) {
      this.data = data;
  }
  public boolean getRepeat() {
      return this.repeat;
  }
  public void setRepeat(boolean repeat) {
      this.repeat = repeat;
  }

}
