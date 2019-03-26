
package com.rezy.dialog.common.utils.interval;

import java.io.Serializable;

/**
 * @ClassName:  Interval
 * @Description: 该类用于定义区间 例如：[1, 10]
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25 
 */
public class Interval implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer start;// 区间开始数字
    public Integer end;// 区间结束数字

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Interval(Integer start ,Integer end) {
        this.start = start;
        this.end = end;
    }
}
