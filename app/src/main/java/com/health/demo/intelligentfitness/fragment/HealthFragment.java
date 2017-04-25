package com.health.demo.intelligentfitness.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.health.demo.intelligentfitness.HistoryActivity;
import com.health.demo.intelligentfitness.R;
import com.health.demo.intelligentfitness.SetPlanActivity;
import com.health.demo.intelligentfitness.config.Constant;
import com.health.demo.intelligentfitness.service.StepService;
import com.health.demo.intelligentfitness.util.SharedPreferencesUtils;
import com.health.demo.intelligentfitness.util.StepCountModeDispatcher;
import com.health.demo.intelligentfitness.view.StepArcView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment implements Handler.Callback {
    @InjectView(R.id.tv_data)
    TextView tvData;
    @InjectView(R.id.cc)
    StepArcView cc;
    @InjectView(R.id.tv_set)
    TextView tvSet;
    @InjectView(R.id.tv_isSupport)
    TextView tvIsSupport;
    private SharedPreferencesUtils sp;
    private Handler delayHandler;
    private boolean isBind = false;
    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        ButterKnife.inject(this, view);
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initData() {
        sp = new SharedPreferencesUtils(getActivity());
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
        cc.setCurrentCount(Integer.parseInt(planWalk_QTY), 0);
        if (StepCountModeDispatcher.isSupportStepCountSensor(getActivity())) {
            tvIsSupport.setText("计步中...");
            delayHandler = new Handler(this);
            setupService();
        } else {
            tvIsSupport.setText("该设备不支持计步");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_data, R.id.tv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_data:
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
            case R.id.tv_set:
                startActivity(new Intent(getActivity(), SetPlanActivity.class));
                break;
        }
    }

    /**
     * 从service服务中拿到步数
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.MSG_FROM_SERVER:
                String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
                cc.setCurrentCount(Integer.parseInt(planWalk_QTY), msg.getData().getInt("step"));
                break;
        }
        return false;
    }
    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(getActivity(), StepService.class);
        isBind = getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);
    }


    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) {
           getActivity().unbindService(conn);
        }
    }
}
