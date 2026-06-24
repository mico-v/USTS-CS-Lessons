import { defineConfig } from 'vitepress'
import mathjax3 from 'markdown-it-mathjax3'

export default defineConfig({
  title: 'USTS CS Lessons',
  description: 'USTS 计算机课程代码与实验文档',
  lang: 'zh-CN',
  base: '/USTS-CS-Lessons/',
  cleanUrls: true,
  lastUpdated: true,
  markdown: {
    config: (md) => {
      md.use(mathjax3)
    }
  },
  themeConfig: {
    nav: [
      { text: '首页', link: '/' },
      { text: 'Java 程序设计', link: '/courses/java-programming/' },
      { text: '面向对象程序设计', link: '/courses/object-oriented-programming/' },
      { text: '计算机程序设计基础', link: '/courses/programming-foundations/' },
      { text: '线性代数', link: '/courses/linear-algebra/' }
    ],
    sidebar: {
      '/courses/object-oriented-programming/': [
        { text: '课程概览', link: '/courses/object-oriented-programming/' },
        {
          text: '复习讲义',
          items: [
            { text: '01 Java 环境与面向对象入门', link: '/courses/object-oriented-programming/01-intro-oop' },
            { text: '02 Java 基础语法', link: '/courses/object-oriented-programming/02-java-basics' },
            { text: '03 类、对象、封装与 static', link: '/courses/object-oriented-programming/03-class-object-encapsulation' },
            { text: '04 UML、嵌套类与类对象练习', link: '/courses/object-oriented-programming/04-uml-nested-classes-practice' },
            { text: '05 继承、构造链与方法重写', link: '/courses/object-oriented-programming/05-inheritance-overriding' },
            { text: '06 类型转换、抽象类、接口与多态', link: '/courses/object-oriented-programming/06-casting-abstract-interface-polymorphism' },
            { text: '07 数组、字符串、泛型与集合', link: '/courses/object-oriented-programming/07-arrays-strings-generics-collections' },
            { text: '08 异常处理与输入输出流', link: '/courses/object-oriented-programming/08-exceptions-io' },
            { text: '09 线程基础、创建方式与生命周期', link: '/courses/object-oriented-programming/09-threads-basics-lifecycle' },
            { text: '10 线程同步、死锁、线程池与 JUC', link: '/courses/object-oriented-programming/10-thread-sync-pools' }
          ]
        }
      ],
      '/courses/java-programming/': [
        { text: '课程概览', link: '/courses/java-programming/' },
        { text: 'Project 01', link: '/courses/java-programming/project-01' },
        { text: 'Project 02', link: '/courses/java-programming/project-02' },
        { text: 'Project 03', link: '/courses/java-programming/project-03' },
        { text: 'Project 04', link: '/courses/java-programming/project-04' }
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
      ]
    },
    socialLinks: [
      { icon: 'github', link: 'https://github.com/mico-v/USTS-CS-Lessons' }
    ],
    outline: {
      label: '页面导航',
      level: [2, 3]
    },
    docFooter: {
      prev: '上一页',
      next: '下一页'
    },
    lastUpdated: {
      text: '最后更新'
    }
  }
})
