# PluginApplication
### 插件化概述
首先存在一个宿主和插件、标准，标准即插件要植入宿主必须满足的规则。
其次宿主 module 存在一个代理Activity，代理activity需要做的就是插桩的操作，而内部实际调用的是插件的页面，为插件提供上下文运行环境，这部分也是最麻烦的部分--同步四大组件生命周期。
插件需要实现一套标准，其运行需要的上下文环境由宿主activity传入。
关键部分在于classLoader加载class类以及resource 渲染布局UI。

插件化的优点：
宿主和插件分开编译；宿主和插件可并发开发；解决方法数爆棚的问题

module描述：
#### app 宿主 module 
模拟下载taopiaopiao module 生成的apk 插件 ，并存在一个ProxyActivity代理Activity，代理Activity实际打开的是taopiaopiao的页面
#### PluginStandard
插件标准，需要在宿主中植入的插件，必须符合这套标准，由于插件 module 的activity 没有加载，不存在上下文，需要宿主提供上下文环境才可以启动。
#### taopiaopiao 
插件 module，未安装的app 没有注入context上下文，上下文由宿主提供。

