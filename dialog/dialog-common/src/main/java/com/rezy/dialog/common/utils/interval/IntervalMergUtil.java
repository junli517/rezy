
package com.rezy.dialog.common.utils.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: IntervalMergUtil
 * @Description: 区间合并工具类
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class IntervalMergUtil {

	/**
	 * @Description: 区间合并 如输入: [[1,3],[2,6],[8,10],[15,18]], 输出:
	 *               [[1,6],[8,10],[15,18]] ,解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]。
	 * @param intervals
	 * @return
	 */
	public static List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new ArrayList<>();
		// 输入为空
		if (intervals == null || intervals.size() < 1) {
			return result;
		}
		int len = intervals.size();
		int[] starts = new int[len];
		int[] ends = new int[len];
		// 将首尾整数分别放置在两个数组中
		for (int i = 0; i < len; i++) {
			starts[i] = intervals.get(i).start;
			ends[i] = intervals.get(i).end;
		}
		// 对两个数组进行排序
		Arrays.sort(starts);
		Arrays.sort(ends);
		// 合并重叠区间
		for (int i = 0, j = 0; i < len; i++) {
			if (i == len - 1 || starts[i + 1] > ends[i]) {
				result.add(new Interval(starts[j], ends[i]));
				j = i + 1;
			}
		}
		return result;
	}
}