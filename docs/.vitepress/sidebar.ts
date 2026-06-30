import type { DefaultTheme } from 'vitepress'

export const sidebar: DefaultTheme.Sidebar = {
  '/courses/java/': [
    { text: '课程概览', link: '/courses/java/' },
    {
      text: 'Java 实践课',
      items: [
        { text: '实践课概览', link: '/courses/java/practice/' },
        { text: 'Project 01', link: '/courses/java/practice/project-01' },
        { text: 'Project 02', link: '/courses/java/practice/project-02' },
        { text: 'Project 03', link: '/courses/java/practice/project-03' },
        { text: 'Project 04', link: '/courses/java/practice/project-04' }
      ]
    },
    {
      text: 'OOP 理论课',
      items: [
        { text: '理论课概览', link: '/courses/java/theory/' },
        { text: '01 Java 环境与面向对象入门', link: '/courses/java/theory/01-intro-oop' },
        { text: '02 Java 基础语法', link: '/courses/java/theory/02-java-basics' },
        { text: '03 类、对象、封装与 static', link: '/courses/java/theory/03-class-object-encapsulation' },
        { text: '04 UML、嵌套类与类对象练习', link: '/courses/java/theory/04-uml-nested-classes-practice' },
        { text: '05 继承、构造链与方法重写', link: '/courses/java/theory/05-inheritance-overriding' },
        { text: '06 类型转换、抽象类、接口与多态', link: '/courses/java/theory/06-casting-abstract-interface-polymorphism' },
        { text: '07 数组、字符串、泛型与集合', link: '/courses/java/theory/07-arrays-strings-generics-collections' },
        { text: '08 异常处理与输入输出流', link: '/courses/java/theory/08-exceptions-io' },
        { text: '09 线程基础、创建方式与生命周期', link: '/courses/java/theory/09-threads-basics-lifecycle' },
        { text: '10 线程同步、死锁、线程池与 JUC', link: '/courses/java/theory/10-thread-sync-pools' }
      ]
    }
  ],
  '/courses/foundation-of-computer-science/': [
    { text: '课程概览', link: '/courses/foundation-of-computer-science/' },
    {
      text: '课程讲义',
      items: [
        { text: '第1章 信息技术概述', link: '/courses/foundation-of-computer-science/chapter-1' },
        { text: '第2章 计算机硬件', link: '/courses/foundation-of-computer-science/chapter-2' },
        { text: '第3章 计算机软件', link: '/courses/foundation-of-computer-science/chapter-3' },
        { text: '第4章 数字媒体及应用', link: '/courses/foundation-of-computer-science/chapter-4' },
        { text: '第5章 计算机网络与互联网', link: '/courses/foundation-of-computer-science/chapter-5' },
        { text: '第6章 信息系统与数据库系统', link: '/courses/foundation-of-computer-science/chapter-6' }
      ]
    }
  ],
  '/courses/linear-algebra/': [
    { text: '课程概览', link: '/courses/linear-algebra/' },
    { text: '模拟试卷一', link: '/courses/linear-algebra/mock-exam-01' },
    { text: '模拟试卷二', link: '/courses/linear-algebra/mock-exam-02' },
    { text: '模拟试卷三', link: '/courses/linear-algebra/mock-exam-03' }
  ],
  '/courses/programming-foundations/': [
    { text: '课程概览', link: '/courses/programming-foundations/' },
    {
      text: '作业',
      items: [
        { text: 'Homework 01', link: '/courses/programming-foundations/homework-01' },
        { text: 'Homework 02', link: '/courses/programming-foundations/homework-02' },
        { text: 'Homework 03', link: '/courses/programming-foundations/homework-03' },
        { text: 'Homework 04', link: '/courses/programming-foundations/homework-04' },
        { text: 'Homework 05', link: '/courses/programming-foundations/homework-05' },
        { text: 'Homework 06', link: '/courses/programming-foundations/homework-06' },
        { text: 'Homework 07', link: '/courses/programming-foundations/homework-07' },
        { text: 'Homework 08', link: '/courses/programming-foundations/homework-08' }
      ]
    },
    {
      text: '实验',
      items: [
        { text: 'Lab 01', link: '/courses/programming-foundations/lab-01' },
        { text: 'Lab 02', link: '/courses/programming-foundations/lab-02' },
        { text: 'Lab 03', link: '/courses/programming-foundations/lab-03' },
        { text: 'Lab 04', link: '/courses/programming-foundations/lab-04' },
        { text: 'Lab 05', link: '/courses/programming-foundations/lab-05' },
        { text: 'Lab 06', link: '/courses/programming-foundations/lab-06' },
        { text: 'Lab 07', link: '/courses/programming-foundations/lab-07' },
        { text: 'Lab 08', link: '/courses/programming-foundations/lab-08' },
        { text: 'Lab 09', link: '/courses/programming-foundations/lab-09' }
      ]
    },
    { text: '期末项目', link: '/courses/programming-foundations/final' },
    { text: '技巧笔记', link: '/courses/programming-foundations/tips' }
  ],
  '/courses/advanced-mathematics/': [
    { text: '课程概览', link: '/courses/advanced-mathematics/' },
    {
      text: '复习讲义',
      items: [
        { text: '第七章 微分方程', link: '/courses/advanced-mathematics/ch7' },
        { text: '第八章 向量代数与空间解析几何', link: '/courses/advanced-mathematics/ch8' },
        { text: '第九章 多元函数微分学', link: '/courses/advanced-mathematics/ch9' },
        { text: '第十章 重积分', link: '/courses/advanced-mathematics/ch10' },
        { text: '第十一章 曲线积分与曲面积分', link: '/courses/advanced-mathematics/ch11' },
        { text: '第十二章 无穷级数', link: '/courses/advanced-mathematics/ch12' }
      ]
    }
  ],
  '/courses/physics/': [
    { text: '课程概览', link: '/courses/physics/' },
    { text: 'A 卷原题与解析', link: '/courses/physics/exam-a' },
    { text: 'B 卷原题与解析', link: '/courses/physics/exam-b' },
    { text: '大学物理 B（一）复习知识讲义', link: '/courses/physics/review-notes' }
  ]
}
