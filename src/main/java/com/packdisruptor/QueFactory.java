package com.packdisruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

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
public class QueFactory<T> {

    public ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("disruptor-pool-%d").build();
    private Executor pool = new ThreadPoolExecutor(10, 200, 5L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    ;
    private QueEventFactory<T> factory;
    //必须是2的n次方
    //private int bufferSize = 4096;
    private int bufferSize = 1048576;
    private Disruptor<QueEvent<T>> disruptor;
    private IProcess<T> process;

    public QueFactory(IProcess<T> process) {
        if (null != process){
            this.process = process;
        }else {
            throw new NullPointerException(IProcess.class.getName() + " Can not be empty.");
        }
    }

    public QueFactory(Executor pool, Integer queDepth,IProcess<T> process) {
        if (null != pool) {
            this.pool = pool;
        }
        if (null != queDepth) {
            this.bufferSize = queDepth;
        }
        if (null != process){
            this.process = process;
        }else {
            throw new NullPointerException(IProcess.class.getName() + " Can not be empty.");
        }
    }

    public QueEventFactory<T> getEventFactory() {
        if (null == factory) {
            factory = new QueEventFactory<T>();
        }
        return factory;
    }

    private void init() {
        if(null == factory){
            this.getEventFactory();
        }
        this.disruptor = new Disruptor<QueEvent<T>>(factory,bufferSize,pool, ProducerType.MULTI, new YieldingWaitStrategy());
        this.disruptor.handleEventsWith(new QueEventHandle<T>(process));
        this.disruptor.start();
        this.disruptor.getRingBuffer();
    }

    public void start(){
        init();
    }

    public QueEventProducer<T> createProducer(){
        RingBuffer<QueEvent<T>> ringBuffer = disruptor.getRingBuffer();
        QueEventProducer<T> eventProducer = new QueEventProducer<T>(ringBuffer);
        return eventProducer;
    }
}
