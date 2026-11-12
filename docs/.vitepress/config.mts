import { defineConfig } from 'vitepress'
import mathjax3 from 'markdown-it-mathjax3'
import { nav } from './nav'
import { sidebar } from './sidebar'

/**
 * 给表格单元格写入 data-label（取自所在列的表头文本），
 * 供窄屏下把表格重排为卡片时显示字段名。
 */
function tableDataLabels(md: any) {
  md.core.ruler.push('table_data_labels', (state: any) => {
    const tokens = state.tokens
    for (let i = 0; i < tokens.length; i += 1) {
      if (tokens[i].type !== 'table_open') continue
      const headers: string[] = []
      let j = i + 1
      for (; j < tokens.length && tokens[j].type !== 'thead_close'; j += 1) {
        if (tokens[j].type === 'th_open') {
          const inline = tokens[j + 1]
          const raw = inline && inline.type === 'inline' ? inline.content : ''
          headers.push(raw.replace(/<[^>]*>/g, '').trim())
        }
      }
      let col = 0
      for (let k = j; k < tokens.length; k += 1) {
        const token = tokens[k]
        if (token.type === 'tr_open') {
          col = 0
          continue
        }
        if (token.type === 'td_open') {
          token.attrSet('data-label', headers[col] || '')
          col += 1
        }
        if (token.type === 'table_close') break
      }
    }
  })
}

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
      md.use(tableDataLabels)
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
