package com.gwh.pluginapplication.kotlin

/**
 *Created by Guo Wenhui
 *2020/10/29
 *对象声明  单例
 **/
object One {
  fun print(){
    println("this is one 对象申明")
  }

}

fun main(args:Array<String>){
  One.print()
}