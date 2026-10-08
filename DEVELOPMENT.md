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

## 第一版实现与构建

保留登录、课程、活动和手动签到；后台监听、刷课、答题不属于本轮。
代码 namespace 仍为上游名称，安装包 applicationId 已独立。首次安装需要重新登录，不会接管原版账号数据。
上游发布工作流保存在 docs/upstream-workflows；个人工作流不会向原作者服务上传。
Stackbricks、友盟、Sentry 和远程排行榜已移除，不再需要 GHP_TOKEN。地图 SDK 仍需百度服务。

GitHub Actions → Personal Android build：push/PR 默认 assembleDebug，手动运行可选择 release。产物在对应运行的 Artifacts，包含 APK 和 SHA256SUMS.txt；未自动上传到 Releases。
只读静态检查命令：

```bash
python3 scripts/check-personal-source.py
bash scripts/check-personal-git.sh
git diff --check
```

正式签名在 GitHub 仓库 Settings → Secrets and variables → Actions 配置：

| Secret | 内容 |
| --- | --- |
| ANDROID_KEYSTORE_BASE64 | 个人 keystore 的 Base64，不得使用上游密钥 |
| ANDROID_KEYSTORE_PASSWORD | keystore 密码 |
| ANDROID_KEY_ALIAS | 个人签名别名 |
| ANDROID_KEY_PASSWORD | 私钥密码 |
| BAIDU_MAP_API_KEY | 正式包名和正式签名对应的百度地图 Key |
| BAIDU_MAP_API_KEY_DEBUG | 可选，调试包和其签名对应的地图 Key |

工作流缺少正式签名参数会失败，不回退到作者或临时密钥。私钥只在 runner 临时目录解码，结束时删除。不要把私钥、密码或学习通账号提交到 Git。
本地若后续需要编译，同名环境变量适用，ANDROID_KEYSTORE_PATH 指向个人 keystore；本轮默认不在本地编译。
调试构建每次 CI 的临时签名可能不同，不能作为给长期使用者的稳定更新渠道。正式签名必须妥善备份并保持一致。

地图 Key 尚未配置时，地图和位置选择界面会提示不可用；普通签到不依赖地图，但任何要求位置信息的签到都需先完成地图配置与实机验证。不能仅凭构建成功承诺签到成功。

分发前应核验 APK 包名、签名指纹、合并权限和 SHA-256，并实机检查首次同意条款、登录/退出、课程刷新、普通及扫码签到、无地图 Key 提示、打开个人发布页、与原版并存。
本轮未做本地编译、真机验证、恶意软件扫描或源码与 APK 可复现比对。云端构建状态以 Actions 运行结果为准。

## 远程状态

已通过 GitHub 插件核验个人账号 yolo023。本地 origin 指向 https://github.com/yolo023/chaoxing-auto-ai.git。
远程仓库为个人 Fork。底座提交基于现有 main 追加，保留 Fork 中已有的上游更新。
远程写入通过已连接的个人 GitHub 插件完成，本地没有保存 GitHub 访问令牌。
