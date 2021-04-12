// Generated by data binding compiler. Do not edit!
package com.xiekang.bluetooths.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiekang.bluetooths.R;
import com.xiekang.bluetooths.bluetooths.oxgen.draw.DrawThreadNW;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityBluetoothConectBinding extends ViewDataBinding {
  @NonNull
  public final DrawThreadNW DrawThreadNW;

  @NonNull
  public final Button btnAiaoleSugar;

  @NonNull
  public final Button btnAiaoletemp;

  @NonNull
  public final Button btnAikang;

  @NonNull
  public final Button btnAikeSugar;

  @NonNull
  public final Button btnKadik;

  @NonNull
  public final Button btnKangtaiOxgen;

  @NonNull
  public final Button btnKangtaiUri;

  @NonNull
  public final Button btnKruikangOxgen;

  @NonNull
  public final Button btnLepu;

  @NonNull
  public final Button btnQingniu;

  @NonNull
  public final Button btnRestart;

  @NonNull
  public final Button btnStop;

  @NonNull
  public final Button btnSugarOxygen;

  @NonNull
  public final Button btnTida;

  @NonNull
  public final Button btnXueya;

  @NonNull
  public final LinearLayout line1;

  @NonNull
  public final TextView tvResult;

  @NonNull
  public final TextView tvStuat;

  protected ActivityBluetoothConectBinding(Object _bindingComponent, View _root,
      int _localFieldCount, DrawThreadNW DrawThreadNW, Button btnAiaoleSugar, Button btnAiaoletemp,
      Button btnAikang, Button btnAikeSugar, Button btnKadik, Button btnKangtaiOxgen,
      Button btnKangtaiUri, Button btnKruikangOxgen, Button btnLepu, Button btnQingniu,
      Button btnRestart, Button btnStop, Button btnSugarOxygen, Button btnTida, Button btnXueya,
      LinearLayout line1, TextView tvResult, TextView tvStuat) {
    super(_bindingComponent, _root, _localFieldCount);
    this.DrawThreadNW = DrawThreadNW;
    this.btnAiaoleSugar = btnAiaoleSugar;
    this.btnAiaoletemp = btnAiaoletemp;
    this.btnAikang = btnAikang;
    this.btnAikeSugar = btnAikeSugar;
    this.btnKadik = btnKadik;
    this.btnKangtaiOxgen = btnKangtaiOxgen;
    this.btnKangtaiUri = btnKangtaiUri;
    this.btnKruikangOxgen = btnKruikangOxgen;
    this.btnLepu = btnLepu;
    this.btnQingniu = btnQingniu;
    this.btnRestart = btnRestart;
    this.btnStop = btnStop;
    this.btnSugarOxygen = btnSugarOxygen;
    this.btnTida = btnTida;
    this.btnXueya = btnXueya;
    this.line1 = line1;
    this.tvResult = tvResult;
    this.tvStuat = tvStuat;
  }

  @NonNull
  public static ActivityBluetoothConectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_bluetooth_conect, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityBluetoothConectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityBluetoothConectBinding>inflateInternal(inflater, R.layout.activity_bluetooth_conect, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityBluetoothConectBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_bluetooth_conect, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityBluetoothConectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityBluetoothConectBinding>inflateInternal(inflater, R.layout.activity_bluetooth_conect, null, false, component);
  }

  public static ActivityBluetoothConectBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityBluetoothConectBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityBluetoothConectBinding)bind(component, view, R.layout.activity_bluetooth_conect);
  }
}