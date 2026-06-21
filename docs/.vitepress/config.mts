import { defineConfig } from 'vitepress'

export default defineConfig({
  title: 'USTS CS Lessons',
  description: 'USTS 计算机课程代码与实验文档',
  lang: 'zh-CN',
  cleanUrls: true,
  lastUpdated: true,
  themeConfig: {
    nav: [
      { text: '首页', link: '/' },
      { text: 'Java 程序设计', link: '/courses/java-programming/' },
      { text: '计算机程序设计基础', link: '/courses/programming-foundations/' }
    ],
    sidebar: {
      '/courses/java-programming/': [
        { text: '课程概览', link: '/courses/java-programming/' },
        { text: 'Project 01', link: '/courses/java-programming/project-01' },
        { text: 'Project 02', link: '/courses/java-programming/project-02' },
        { text: 'Project 03', link: '/courses/java-programming/project-03' },
        { text: 'Project 04', link: '/courses/java-programming/project-04' }
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
