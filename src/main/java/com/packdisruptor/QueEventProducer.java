package com.packdisruptor;

import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.RingBuffer;

/**
 * ------------------------------------------------------
 * |                                                    |
 * |                       _oo0oo_                      |
 * |                      o8888888o                     |
 * |                      88" . "88                     |
 * |                      (| -_- |)                     |
 * |                      0\  =  /0                     |
 * |                    ___/`---'\___                   |
 * |                  .' \\|     |// '.                 |
 * |                 / \\|||  :  |||// \                |
 * |                / _||||| -:- |||||- \               |
 * |               |   | \\\  -  /// |   |              |
 * |               | \_|  ''\---/''  |_/ |              |
 * |               \  .-\__  '-'  ___/-. /              |
 * |             ___'. .'  /--.--\  `. .'___            |
 * |          ."" '<  `.___\_<|>_/___.' >' "".          |
 * |         | | :  `- \`.;`\ _ /`;.`/ - ` : | |        |
 * |         \  \ `_.   \_ __\ /__ _/   .-` /  /        |
 * |     =====`-.____`.___ \_____/___.-`___.-'=====     |
 * |                       `=---='                      |
 * |                                                    |
 * |     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    |
 * |                                                    |
 * |               佛祖保佑         永无BUG               |
 * |                                                    |
 * ------------------------------------------------------
 * <p>
 * com.packdisruptor [workspace_idea_01]
 * Created by Richard on 2018/01/19 0019
 *
 * @author Richard on 2018/01/19 0019
 */
public class QueEventProducer<T> {

    private final RingBuffer<QueEvent<T>> ringBuffer;

    public QueEventProducer(RingBuffer<QueEvent<T>> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public final EventTranslatorThreeArg<QueEvent<T>,String,Boolean,T> TRANSLATOR = new EventTranslatorThreeArg<QueEvent<T>,String,Boolean,T>(){

        @Override
        public void translateTo(QueEvent<T> tQueEvent, long sequence, String strValue, Boolean boolValue, T t) {
            tQueEvent.setStrValue(strValue);
            tQueEvent.setBoolValue(boolValue);
            tQueEvent.setT(t);
        }
    };

    public void onData(String strValue, Boolean boolValue, T t){
        ringBuffer.publishEvent(TRANSLATOR,strValue,boolValue,t);
    }
}
