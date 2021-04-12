package com.xiekang.bluetooths;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.creative.base.BaseDate;
import com.qn.device.constant.QNIndicator;
import com.qn.device.out.QNScaleData;
import com.xiekang.bluetooths.base.BaseBingActivity;
import com.xiekang.bluetooths.bean.NewUIData;
import com.xiekang.bluetooths.bluetooths.QNBleDeviceUtlis;
import com.xiekang.bluetooths.bluetooths.bloopress.Bloodpress_Bluetooth_Utlis;
import com.xiekang.bluetooths.bluetooths.bloopress.Bloodpress_intenface;
import com.xiekang.bluetooths.databinding.ActivityBluetoothConectBinding;
import com.xiekang.bluetooths.interfaces.Blutooth_Search;
import com.xiekang.bluetooths.interfaces.GetBloodfat;
import com.xiekang.bluetooths.interfaces.GetBodyfat;
import com.xiekang.bluetooths.interfaces.GetOxgen;
import com.xiekang.bluetooths.interfaces.GetTemperature;
import com.xiekang.bluetooths.interfaces.GetUriDate;
import com.xiekang.bluetooths.interfaces.Getbloodsuar;
import com.xiekang.bluetooths.utlis.LogUtils;
import com.xiekang.bluetooths.base.PermissionUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.xiekang.bluetooths.utlis.Common.AlKAN;
import static com.xiekang.bluetooths.utlis.Common.BC01;
import static com.xiekang.bluetooths.utlis.Common.Bioland_BGM;
import static com.xiekang.bluetooths.utlis.Common.Bioland_BPM;
import static com.xiekang.bluetooths.utlis.Common.Bioland_IT;
import static com.xiekang.bluetooths.utlis.Common.Bluetooth_BP;
import static com.xiekang.bluetooths.utlis.Common.CardioChek;
import static com.xiekang.bluetooths.utlis.Common.LPM311;
import static com.xiekang.bluetooths.utlis.Common.OGM;
import static com.xiekang.bluetooths.utlis.Common.POD;
import static com.xiekang.bluetooths.utlis.Common.QN_Scale;
import static com.xiekang.bluetooths.utlis.Common.SpO2;
import static com.xiekang.bluetooths.utlis.Common.iGate;

//蓝牙搜索页面
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BluetoothConectActivity extends BaseBingActivity<ActivityBluetoothConectBinding> {
  /**
   * 绘制波形的线程
   */
  private Thread mDrawThread = null;
  @Override
  public int getLayoutId() {
    return R.layout.activity_bluetooth_conect;
  }

  @SuppressLint("ResourceType")
  @Override
  public void initView() {
    PermissionUtils.checkBluetoothPermission(BluetoothConectActivity.this);
    mViewBinding.DrawThreadNW.setDrawColor(getResources().getColor(R.color.purple_200));
    mViewBinding.tvResult.setVisibility(View.GONE);
    BluetoothMangers.getInstance().init(true,1000000000,1000000000);
    QNBleDeviceUtlis.getInstance().initQinniu();
    mViewBinding.btnStop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("");
        setRsult("");
        BluetoothMangers.getInstance().Stop();
      }
    });
    mViewBinding.btnRestart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        BluetoothMangers.getInstance().Restart();
      }
    });
    mViewBinding.btnQingniu.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        BluetoothMangers.getInstance().StartSearch(QN_Scale, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
              BluetoothMangers.getInstance().StopScan();
              QNBleDeviceUtlis.getInstance().Connect(remoteDevice.get(0), scanRecord.get(0),"text", 170, 1, "1993-10-11", new GetBodyfat<QNScaleData>() {

                @Override
                public void succed() {
                  Stautu("连接上了");
                }

                @Override
                public void err() {
                  Stautu("连接断开了");
                }

                @Override
                public void getBodyfat(QNScaleData all, String height) {
                  double weight= all.getItem(QNIndicator.TYPE_WEIGHT).getValue();
                  double bmi= all.getItem(QNIndicator.TYPE_BMI).getValue();
                  LogUtils.e( "收到体重:"+weight);
                  setRsult("体重："+weight+"\n身高："+height+"\nBMI:"+bmi);

                }
              });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });

      }
    });

    mViewBinding.btnXueya.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(Bioland_BPM, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0), new Bloodpress_intenface<Bloodpress_Bluetooth_Utlis.Info>() {
              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }

              @Override
              public void current(int date) {
                setRsult("袖带压：" + date);

              }

              @Override
              public void getDate(Bloodpress_Bluetooth_Utlis.Info uiData) {
                setRsult("血压：" + uiData.toString());
              }
            });
          }

          @Override
          public void Timeout() {
            setRsult("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnKruikangOxgen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(POD, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0), new GetOxgen<BaseDate.Wave>() {

              @Override
              public void succed() {
                Stautu("连接上了");
                if (mDrawThread == null) {
                  mDrawThread = new Thread(mViewBinding.DrawThreadNW, "DrawPOD_Thread");
                  mDrawThread.start();
                } else if (mViewBinding.DrawThreadNW.isPause()) {
                  mViewBinding.DrawThreadNW.Continue();
                }
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }

              @Override
              public void getOxgen(final int spo2, final int pr) {
                    setRsult("oxgen"+spo2+"pr\r\n"+pr);
              }

              @Override
              public void startDraw(BaseDate.Wave wave) {
                LogUtils.e(wave.toString());
                mViewBinding.DrawThreadNW.setSPO_WAVE(wave);

              }

            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });

    mViewBinding.btnKangtaiOxgen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(SpO2, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0), new GetOxgen<BaseDate.Wave>() {

              @Override
              public void succed() {
                Stautu("连接上了");
                if (mDrawThread == null) {
                  mDrawThread = new Thread(mViewBinding.DrawThreadNW, "DrawPOD_Thread");
                  mDrawThread.start();
                } else if (mViewBinding.DrawThreadNW.isPause()) {
                  mViewBinding.DrawThreadNW.Continue();
                }
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }

              @Override
              public void getOxgen(final int spo2, final int pr) {
                    setRsult("oxgen"+spo2+"pr\r\n"+pr);
              }

              @Override
              public void startDraw(BaseDate.Wave wave) {
                LogUtils.e(wave.toString());
                mViewBinding.DrawThreadNW.setSPO_WAVE(wave);

              }

            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnAikang.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(iGate, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new GetBloodfat() {
              @Override
              public void getbloodfat(float chlo, float trig, float hdl, float ldl) {
                setRsult(chlo+"\r\n"+ trig+"\r\n"+  hdl+"\r\n"+  ldl);
              }

              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }
            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnKadik.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(CardioChek, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new GetBloodfat() {
              @Override
              public void getbloodfat(float chlo, float trig, float hdl, float ldl) {
                setRsult(chlo+"\r\n"+ trig+"\r\n"+  hdl+"\r\n"+  ldl);
              }

              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }
            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnLepu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(LPM311, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new GetBloodfat() {
              @Override
              public void getbloodfat(float chlo, float trig, float hdl, float ldl) {
                setRsult(chlo+"\r\n"+ trig+"\r\n"+  hdl+"\r\n"+  ldl);
              }

              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }
            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnTida.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(Bluetooth_BP, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new GetTemperature(){
              @Override
              public void succed() {
                Stautu("连接上了");
              }
              @Override
              public void errCode(String messager) {
                setRsult(messager);
              }
              @Override
              public void err() {
                Stautu("连接断开了");
              }

              @Override
              public void getbloodfat(String chlo) {
                 setRsult(chlo);
              }
            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });

    mViewBinding.btnAiaoletemp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(Bioland_IT, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new GetTemperature(){
              @Override
              public void succed() {
                Stautu("连接上了");
              }
              @Override
              public void errCode(String messager) {
                setRsult(messager);
              }
              @Override
              public void err() {
                Stautu("连接断开了");
              }

              @Override
              public void getbloodfat(String chlo) {
                setRsult(chlo);
              }
            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnAikeSugar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(OGM, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new Getbloodsuar(){
              @Override
              public void getbloodsugar(float bloodsugar) {
                setRsult(bloodsugar+"");
              }

              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }

            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnAiaoleSugar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(Bioland_BGM, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new Getbloodsuar(){
              @Override
              public void getbloodsugar(float bloodsugar) {
                setRsult(bloodsugar+"");
              }

              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }

            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });
    mViewBinding.btnKangtaiUri.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(BC01, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0),new GetUriDate<NewUIData>(){
              @Override
              public void getBodyfat(NewUIData uiData) {
                setRsult(uiData.toString()+"");
              }


              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }

            });
          }

          @Override
          public void Timeout() {
            Stautu("搜索完成");
          }
        });
      }
    });

    mViewBinding.btnSugarOxygen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Stautu("正在搜索设备，请保证设备为开启状态");
        setRsult("");
        BluetoothMangers.getInstance().StartSearch(AlKAN, new Blutooth_Search() {
          @Override
          public void Searched(List<BluetoothDevice> remoteDevice,List<byte[]> scanRecord) {
            BluetoothMangers.getInstance().StopScan();
            BluetoothMangers.getInstance().Connect(remoteDevice.get(0), new Getbloodsuar() {

              @Override
              public void getbloodsugar(float bloodsugar) {
                setRsult("血红蛋白：" +bloodsugar);
              }

              @Override
              public void succed() {
                Stautu("连接上了");
              }

              @Override
              public void err() {
                Stautu("连接断开了");
              }
            });
          }

          @Override
          public void Timeout() {
            setRsult("搜索完成");
          }
        });
      }
    });


  }

  private void Stautu(String messager) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mViewBinding.tvStuat.setText("连接状态:" + messager);
        mViewBinding.tvResult.setVisibility(View.VISIBLE);
      }
    });
  }

  private void setRsult(String text) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mViewBinding.tvResult.setText(text);
        mViewBinding.tvResult.setVisibility(View.VISIBLE);
      }
    });

  }

}
