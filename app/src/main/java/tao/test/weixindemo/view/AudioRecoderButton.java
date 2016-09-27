package tao.test.weixindemo.view;

import android.content.Context;
import android.support.annotation.StringDef;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import tao.test.weixindemo.R;

/**
 * Created by lisongtao on 2016/9/2 09:27.
 * desc：自定义按钮
 */
public class AudioRecoderButton extends Button {
    public AudioRecoderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AudioRecoderButton(Context context) {
        this(context, null);
    }

    private static final int State_Normal = 1;//正常状态
    private static final int State_Recoding = 2;//录音状态
    private static final int State_want_cancle = 3;//取消状态

    private static final int Distance_Y_CANCEL = 50;//在竖直方向上移动的有效范围
    //当前状态（默认是正常状态）
    private int Current_State = State_Normal;
    //判断是否在录音
    private boolean isRecording = false;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        //获取当前坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {

            case MotionEvent.ACTION_DOWN://按下
                //按下时
                isRecording = true;
                //当按下按钮时，改变当前状态为录音状态
                changeState(State_Recoding);
                break;
            case MotionEvent.ACTION_MOVE://移动
                //1.移动时首先判断当前是不是正在录音
                if (isRecording) {
                    //2.然后根据当前坐标位置判断是不是要取消录音
                    if (wantTocancel(x, y)) {
                        //如果是要取消录音，就改变当前状态为取消录音状态
                        changeState(State_want_cancle);
                    } else {

                        changeState(State_Recoding);
                    }

                }


                break;

            case MotionEvent.ACTION_UP://抬起
                //抬起手指时，先判断当前状态
                if (Current_State == State_Recoding) {
                    //如果是正在录音

                } else if (Current_State == State_want_cancle) {

                    //如果是取消录音状态
                }

                //恢复初始状态
                reset();
                break;
        }

        return super.onTouchEvent(event);
    }

    //恢复初始状态
    private void reset() {
        isRecording = false;
        changeState(State_Normal);
    }

    /**
     * 判断是否超出按钮的有效范围
     *
     * @param x x方向上移动的距离
     * @param y Y方向上移动的距离
     * @return
     */
    private boolean wantTocancel(int x, int y) {

        //在水平方向上超出按钮的有效范围
        if (x < 0 || x > getWidth()) {

            return true;
        }

        //在竖直方向上超出范围
        if (y < -Distance_Y_CANCEL || y > getHeight() + Distance_Y_CANCEL) {

            return true;
        }


        return false;
    }


    //改变当前状态
    private void changeState(int state) {

        //只有在state和当前的current_state不同时才会去改变当前状态
        if (Current_State != state) {
            //改变当前状态
            Current_State = state;
            switch (state) {

                case State_Normal://正常状态
                    setSelected(false);
                    setText(R.string.audiobutton_normal);
                    break;
                case State_Recoding://录音状态
                    setSelected(true);
                    setText(R.string.audiobutton_recording);
                    break;
                case State_want_cancle://取消状态
                    setSelected(true);
                    setText(R.string.audiobutton_cancle);
                    break;
            }
        }


    }


}
