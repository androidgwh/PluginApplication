package com.gwh.pluginapplication.kotlin

/**
 *Created by Guo Wenhui
 *2020/10/29
 *
 * 数据类 不能继承
 * 使用抽象类或者接口在属于同一超类型的多个数据类之间共享属性和方法
 **/

data class OneBean(var name: String,var password:String){

}

fun main() {
  val one=OneBean("tony","123456")
  val oneCopy=one.copy()
  println(one===oneCopy)
}