# 学习助手 · Chaoxing Auto AI

基于 [aquamarine5/ChaoxingSignFaker](https://github.com/aquamarine5/ChaoxingSignFaker) 的个人 Android 分支，遵循 AGPL-3.0，保留上游版权。修改日期：2026-10-08。

第一版保留登录、课程、活动和手动签到流程；不包含后台自动监听、刷课或自动答题。最低 Android 8.0，目前仅 arm64 设备。

## 本分支变化

- 正式包名 `io.github.yolo023.chaoxingautoai`，调试包追加 `.debug`，可以与原版共存。
- 移除友盟、Sentry、远程排行榜、远程名单和赞助配置请求；签到计数保存在本机。
- 移除上游更新 SDK；用户点击后打开[本仓库发布页](https://github.com/yolo023/chaoxing-auto-ai/releases)，不自动下载或安装。
- 禁止明文 HTTP，关闭账号和照片备份、设备迁移；分享二维码使用应用内部链接。
- 移除工作树内的上游签名文件。正式版本只能使用个人签名配置；历史中的上游签名材料不可复用。

## 构建与安装

GitHub Actions 的 **Personal Android build** 生成调试 APK 和 SHA-256 文件。调试包用于验证，CI 临时调试密钥不保证跨次构建可覆盖安装；稳定使用需配置个人正式签名后手动构建 release。该流程不自动发布 Releases。

地图签到需要与包名及签名证书匹配的百度 API Key。未配置时界面明确提示不可用，不会进入地图组件。

详细步骤、签名配置及验收要求见 [DEVELOPMENT.md](DEVELOPMENT.md)。目前源码已做静态检查，未在本地编译、做真机验证或 APK 恶意软件检测，不能把本分支称为已经验证可安装或无病毒的发行版。

[上游原始说明](docs/UPSTREAM_README.md)仅用于来源追溯，其中下载链接、更新说明及功能介绍属于上游项目。
