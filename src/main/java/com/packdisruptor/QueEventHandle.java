package com.packdisruptor;

import com.lmax.disruptor.EventHandler;

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
 * Created by Richard on 2018/01/18 0018
 *
 * @author Richard on 2018/01/18 0018
 */
public class QueEventHandle<T> implements EventHandler<QueEvent<T>> {

    public QueEventHandle(IProcess<T> process){
        this.process = process;
    }

    private IProcess<T> process;

    @Override
    public void onEvent(QueEvent<T> tQueEvent, long sequence, boolean endOfBatch) throws Exception {
        process.onReceive(tQueEvent);
    }
}
