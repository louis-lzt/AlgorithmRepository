package com.louis.algorithm

import java.util.*

/**
 * dec:
 *
 * author : louis
 * email : zhaotian.liu@tcl.com
 * time : 2022/5/31 21:00
 */
public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit {

}

// 有序表
fun threeMap() {
    val treeMap = TreeMap<Int, String>()
    treeMap[7] = "我是7"
    treeMap[5] = "我是5"
    treeMap[4] = "我是4"
    treeMap[3] = "我是3"
    treeMap[9] = "我是9"
    treeMap[2] = "我是2"
    println(treeMap.containsKey(5))
    println(treeMap[5])
    println("${treeMap.firstKey()}, 我最小")
    println("${treeMap.lastKey()}, 我最大")
    println("${treeMap.floorKey(8)}, 在表中所有<=8, 离得最近的")
    println("${treeMap.ceilingKey(8)}, 在表中所有>=8, 离得最近的")
    println("${treeMap.remove(5)}, 删除")
}