## 职责单一
* 搞个专门负责初始化的类：InitManager，把初始化的代码从Application移出来
* BuildConfig是自动生成的，会自己判断debug还是release的


## 每个文件的注释说明
* 用as自带的模版

## 账号，密码，签名
* 放到.gradle/gradle.properties里面，不要放到工程里面，防止泄漏