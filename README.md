# MobPush

mok 消息推送 app

## 二、权限说明

|  序号   | 权限名  | 用途  | 目的  |
|  ----  | ----  | ----  | ----  |
| 1  | android.permission.INTERNET | 连接网络 | 基础网络通信 |
| 2  | android.permission.ACCESS_NETWORK_STATE | 访问GSM网络信息 | 用于选择和优化网络链路，实现稳定的消息推送服务 |
| 3 | android.permission.ACCESS_WIFI_STATE | 访问WiFi网络信息 | 用于选择和优化网络链路，实现稳定的消息推送服务 |
| 4  | android.permission.READ_PHONE_STATE | 获取设备IMEI、SIM序列号等信息 | 用于生成脱敏的终端用户设备唯一性标识 |
| 5  | android.permission.CHANGE_WIFI_STATE | 获取WIFI连接状态 | 用于选择和优化网络链路，实现稳定的消息推送服务 |
| 6  | android.permission.ACCESS_FINE_LOCATION | 访问设备精确位置 | 用于为不同区域的终端用户提供精细化的应景推送内容 |
| 7  | android.permission.ACCESS_COARSE_LOCATION | 访问设备大致位置 | 用于为不同区域的终端用户提供精细化的应景推送内容 |


