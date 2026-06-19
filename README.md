# USTS CS Lessons

本仓库用于备份 USTS 2025 下学期《计算机程序设计基础》课程资料。

## 分支说明

- `main`: 仓库入口说明，仅保留分支用途和访问指引。
- `python`: 课程作业、实验代码、Notebook、数据文件和依赖说明都已迁移到这个分支。

## 查看课程代码

切换到 `python` 分支即可查看完整内容：

```bash
git switch python
```

如果是从远程仓库重新克隆，可以使用：

```bash
git fetch origin
git switch python
```

## 内容范围

`python` 分支包含以下内容：

- `homework-*`: 课程作业与说明
- `lab-*`: 实验代码、报告、流程图和数据文件
- `final`: 期末相关 Notebook 与数据
- `tips`: Python 学习笔记
- `requirements.txt`: Python 依赖列表

## 运行环境

课程代码建议使用 Python 3.9+。进入 `python` 分支后，可按需创建虚拟环境并安装依赖：

```bash
python -m venv .venv
pip install -r requirements.txt
```
