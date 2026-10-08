# 项目开发规范

- 使用 `runCatching` 处理异常，不使用 `try-catch`；协程取消异常应继续传播。
- 使用 `checkPredictable` 和 `requirePredictable`，不使用 Kotlin 标准库的 `check` 和 `require`。两个辅助函数位于 `ChaoxingResponseHelper.kt`，检查失败时抛出 `ChaoxingPredictableException`。
- 生成代码时不生成注释。
- 不要使用`SharedPreference`。
- 程序的单元测试为空，请勿进行测试。
- AI在尝试构建项目时，请勿将构建输出的重定向log文件放在项目目录里面（例如build-result.txt），即使是.gitignore目录也不行，请放在%TEMP%或其他位置，但buildDir无需更改。
- 布尔型变量命名应以 `is` 开头。

## 个人开发分支约定

- 本仓库为个人 GitHub 项目，与公司 GitLab 项目隔离。禁止修改全局 Git 配置或使用公司提交身份。
- 提交前运行 `scripts/check-personal-git.sh`；仅向经过个人 GitHub 身份核验的 origin 推送。
- 上游固定基线为 `1.19.1-stable`，保留许可证、版权和修改来源。
- 第一版只涉及手动进入页面签到，后台监听、刷课和答题不属于本轮范围。
- 默认不在本地编译或运行单测；未验证的构建和真机行为必须如实说明。
- 原作者发布工作流已停用，位于 docs/upstream-workflows，仅供参考。
