package com.xiekang.bluetooths.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;


/**
 * @项目名称 HealthMachineBluetooth1.0
 * @类名 name：com.shenzhen_xiekang.healthmachinebluetooth10.Base
 * @类描述 Activity基类
 * @创建人 hsl20
 * @创建时间 2017/9/14 10:39
 * @修改人
 * @修改时间 time
 * @修改备注 describe
 */
public abstract class BaseBingActivity<T extends ViewDataBinding> extends Activity {
  public T mViewBinding;
  private View rootView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    rootView = getLayoutInflater().inflate(this.getLayoutId(), null, false);
    mViewBinding = DataBindingUtil.setContentView(this, this.getLayoutId());
        initView();

  }

  public abstract int getLayoutId();

  public abstract void initView();




}
