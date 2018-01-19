package com.packdisruptor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
public class QueEvent<T> implements Serializable {

    private static final long serialVersionUID = 3788379639354406150L;

    private String strValue;
    private Integer intValue;
    private Long longValue;
    private Double doubleValue;
    private Boolean boolValue;
    private List<T> listValue;
    private Vector<T> vectorValue;
    //普通map直接new hashmap，线程安全map需要new ConcurrentHashMap
    private Map<String,T> mapValue;
    private T t;

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public List<T> getListValue() {
        return listValue;
    }

    public void setListValue(List<T> listValue) {
        this.listValue = listValue;
    }

    public Vector<T> getVectorValue() {
        return vectorValue;
    }

    public void setVectorValue(Vector<T> vectorValue) {
        this.vectorValue = vectorValue;
    }

    public Map<String, T> getMapValue() {
        return mapValue;
    }

    public void setMapValue(Map<String, T> mapValue) {
        this.mapValue = mapValue;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(Boolean boolValue) {
        this.boolValue = boolValue;
    }
}
