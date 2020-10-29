package com.gwh.pluginapplication.kotlin

/**
 *Created by Guo Wenhui
 *2020/10/29
 *伴生对象--单例
 **/
class TwoSingle private constructor(){
  companion object{
    val instance:TwoSingle by lazy { TwoSingle() }
  }

}

fun main() {
  val instance = TwoSingle.instance
  var instance1 = TwoSingle.instance
  println(instance==instance1)

}