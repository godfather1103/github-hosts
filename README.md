
## GitHub 访问速度慢，图片无法加载？一劳永逸的解决方法

不知道从什么时候我访问github就无法展示图片了，而且有时候（尤其晚上）打开网页速度慢,这个项目就是为了一劳永逸的解决问题.

**应用后的效果就是：网页打开快了，`Github` 图片可以正常加载。**

本项目会实时更新最新的 `hosts` , 你不用在每次去网上找那些可能已经过期的，所以给个 `star` 不迷路。

- 站点地址：[https://github.com/godfather1103/github-hosts](https://github.com/godfather1103/github-hosts)
- 站点地址：[https://gitcode.net/godfather1103/github-hosts](https://gitcode.net/godfather1103/github-hosts)
- 站点地址：[https://gitee.com/godfather1103/github-hosts](https://gitee.com/godfather1103/github-hosts)

## 使用方法

### hosts

内容定时更新，最近更新时间：2025年8月27日 下午1:25:02

```javascript
# GitHub Host Start

185.199.108.154               github.githubassets.com
140.82.113.22                 central.github.com
185.199.111.133               desktop.githubusercontent.com
185.199.108.133               camo.githubusercontent.com
185.199.108.133               github.map.fastly.net
104.244.43.57                 github.global.ssl.fastly.net
46.82.174.68                  gist.github.com
185.199.110.153               github.io
140.82.116.3                  github.com
140.82.116.6                  api.github.com
185.199.109.133               raw.githubusercontent.com
185.199.111.133               user-images.githubusercontent.com
185.199.108.133               favicons.githubusercontent.com
185.199.110.133               avatars5.githubusercontent.com
185.199.108.133               avatars4.githubusercontent.com
185.199.110.133               avatars3.githubusercontent.com
185.199.110.133               avatars2.githubusercontent.com
185.199.111.133               avatars1.githubusercontent.com
185.199.108.133               avatars0.githubusercontent.com
185.199.111.133               avatars.githubusercontent.com
140.82.116.10                 codeload.github.com
52.216.50.81                  github-cloud.s3.amazonaws.com
52.217.91.60                  github-com.s3.amazonaws.com
52.217.228.57                 github-production-release-asset-2e65be.s3.amazonaws.com
54.231.133.33                 github-production-user-asset-6210df.s3.amazonaws.com
3.5.31.183                    github-production-repository-file-5c1aeb.s3.amazonaws.com
185.199.111.153               githubstatus.com
140.82.113.18                 github.community
185.199.110.133               media.githubusercontent.com
138.91.182.224                copilot-proxy.githubusercontent.com
185.199.108.133               cloud.githubusercontent.com
13.107.42.16                  pipelines.actions.githubusercontent.com
185.199.109.133               objects.githubusercontent.com

# Please Star: https://github.com/godfather1103/github-hosts
# Please Star: https://gitcode.net/godfather1103/github-hosts
# Please Star: https://gitee.com/godfather1103/github-hosts
# Update at: 2025年8月27日 下午1:25:02

# GitHub Host End
```

### 手动配置

#### macOS

`hosts` 文件位置： `/etc/hosts`

`macOS` 修改需要管理员权限，所以需要按照如下方式配置：

1. ##### 首先，打开（访达）Finder。

2. ##### 使用组合键 `Shift+Command+G` 打开"前往文件夹"，输入框中输入 `/etc/hosts`。

3. ##### 然后就会跳转到 `hosts` 文件位置。

> 注意：如果你使用`VS Code`，可以直接用`VS Code`修改和保存，不需要复制文件。

复制`hosts`文件到桌面上，鼠标右键右击它，选择「打开方式」—「文本编辑」，打开这个`hosts`文件，把上面的`hosts`内容复制进来。

然后用你修改好的`hosts`文件替换掉：`/etc/hosts` 文件。

注意：如果弹出密码输入框，你需要输入你当前登录账号对应的密码。

最后刷新缓存：

```bash
sudo killall -HUP mDNSResponder
```

#### Windows

`hosts`文件位置：`C:/windows/system32/drivers/etc/hosts`。

将上面提供的 `hosts` 内容追加到`hosts`文件，然后刷新`DNS`缓存：

```bash
ipconfig /flushdns
```

如果你不愿意安装其他软件，那么使用这种手动操作的方式即可，缺点：可能过一段时间 github 访问又慢了，图片无法加载了，你就需要 `star` 我这个项目，然后来获取最新的 hosts 内容去替换你本地的。

**不过我更推荐使用下面 `SwitchHosts` 这种自动更新一劳永逸的方法。**

### 使用 SwitchHosts，远程自动更新

如果对 `SwitchHosts` 感兴趣的同学，可以访问其官网查看：[https://swh.app/zh/](https://swh.app/zh/)

放一张软件的截图，还是很简约好用的。

<img src="https://cdn.jsdelivr.net/gh/isevenluo/pic-bed@master/img/20210428201021.png" style="zoom:50%;" />

这款软件支持 `Windwos` 和 `macOS` 系统，使用方式时一样的。

软件下载地址：[https://github.com/oldj/SwitchHosts/releases](https://github.com/oldj/SwitchHosts/releases)

#### 设置定时同步云端最新hosts

安装好 SwitchHosts 后，打开软件新增一条远程规则：

- Hosts类型：远程
- Hosts标题：github-hosts（自定义即可）
- URL：[https://github.com/godfather1103/github-hosts/raw/master/hosts](https://github.com/godfather1103/github-hosts/raw/master/hosts)
- URL：[https://gitcode.net/godfather1103/github-hosts/-/raw/master/hosts](https://gitcode.net/godfather1103/github-hosts/-/raw/master/hosts)
- URL：[https://gitee.com/godfather1103/github-hosts/raw/master/hosts](https://gitee.com/godfather1103/github-hosts/raw/master/hosts)
- 自动刷新：1小时

<img src="https://cdn.jsdelivr.net/gh/isevenluo/pic-bed@master/img/20210428221655.png" style="zoom: 50%;" />

这样你就可以定时获取最新的 `hosts` 了，再也不用担心 `github` 无法访问，图片加载不了了。

> PS：记着不要忘记点击侧边栏的开关哦～～

<img src="https://cdn.jsdelivr.net/gh/isevenluo/pic-bed@master/img/20210428223116.png" alt="image-20210428223111467" style="zoom:50%;" />


**如果对你有用，不妨 `star` 一下可好。**

