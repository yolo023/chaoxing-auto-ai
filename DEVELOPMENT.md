# 个人安卓开发底座

基于 aquamarine5/ChaoxingSignFaker，AGPL-3.0。保留上游版权和 LICENSE。

- 上游：https://github.com/aquamarine5/ChaoxingSignFaker
- 基线：1.19.1-stable
- 基线提交：e81609c4224a99cfc48e358fc75d1eab9ba76e34
- 个人开发分支：main

## Git 隔离

此目录是独立克隆，拥有独立 .git，不是公司仓库的子模块或 worktree。所有配置使用 --local。
提交署名和远程登录是两套身份：脚本只配置署名，不创建、迁移或打印登录凭据。
个人 GitHub 登录完成并核验账号后，使用该账号的名称、GitHub noreply 邮箱和个人 Fork URL：

```bash
bash scripts/setup-personal-git.sh '<GitHub用户名>' '<该账号的noreply邮箱>' 'https://github.com/<GitHub用户名>/chaoxing-auto-ai.git'
bash scripts/check-personal-git.sh
```

脚本拒绝公司邮箱和非个人同名 GitHub 路径，清空本仓库继承的 credential.helper，禁止向 upstream 推送。
不要执行全局 gh auth setup-git 或修改全局 user.name/user.email；HTTPS 凭据只绑定本仓库或单次操作。
新克隆不会自动启用 hooks，必须先运行 setup-personal-git.sh。本地钩子是误操作防护，不是不可绕过的权限系统。
准备阶段使用空的本地身份阻止继承公司配置；核验 GitHub 账号后，仅在本仓库配置个人身份。

## 功能范围与后续开发

第一版沿用登录、课程、活动、手动签到。尚未增加后台监听、刷课或答题。
原作者的 GitHub Actions 工作流已转存为 .reference 文件，避免 Fork 后触发原作者发布、统计图更新和外部上传。
当前只提供手动触发的源码差异检查，不生成或发布 APK。

正式构建前需完成独立签名、包名和地图配置、更新渠道及统计/错误上报的处理。
上游 Stackbricks 依赖来自 GitHub Packages，构建认证需单独验证；本底座没有放入访问令牌。
上游历史和工作树仍含原有签名材料，禁止作为个人版本发布密钥；新增密钥已加入忽略规则。
当前没有进行本地编译、病毒扫描或真机验证，也未宣称源码与上游 APK 已完成可复现构建校验。

## 远程状态

已通过 GitHub 插件核验个人账号 yolo023。本地 origin 指向 https://github.com/yolo023/chaoxing-auto-ai.git。
远程仓库为个人 Fork。底座提交基于现有 main 追加，保留 Fork 中已有的上游更新。
远程写入通过已连接的个人 GitHub 插件完成，本地没有保存 GitHub 访问令牌。
