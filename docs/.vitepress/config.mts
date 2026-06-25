import { defineConfig } from 'vitepress'
import mathjax3 from 'markdown-it-mathjax3'
import { nav } from './nav'
import { sidebar } from './sidebar'

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
    nav,
    sidebar,
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
