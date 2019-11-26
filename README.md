# mvpCommon   
mvp架构基础库 
#这是一个mvp架构的基础开发库，主要基于Rxjava + Retrofit + Okhttp + Glide 已经处理了沉浸式标题栏相关
其中引入了比较常用的第三方库包含
butterknife，常用注解库
autosize最佳适配方案 
klog最小日志打印
easypermissions 简单权限请求
live-event-bus 消息总线，具备生命周期感知能力，无需解除绑定
BaseRecyclerViewAdapterHelper recycleview通用适配器
本库是自己根据自己的代码习惯粗略封装的，能够满足自己的基本开发需求
在此仅作备份记录

1.build.gradle(project)添加  
repositories {  
        maven { url 'https://jitpack.io' }  
        ...  
    }  
2.build.gradle(app)添加   
dependencies {  
    ...  
    implementation 'com.github.wutianchi:mvpCommon:v1.0+'  
    annotationProcessor rootProject.ext.dependencies["butterknife-compiler"]
}  
3.AndroidManifest.xml添加 其中360和640为设计出图的规格，需要根据自己实际的UI图更改  拷下面的代码需要把《换成<
    《application  
        android:allowBackup="true"  
        android:icon="@mipmap/ic_launcher"  
        android:label="@string/app_name"  
        android:roundIcon="@mipmap/ic_launcher_round"  
        android:supportsRtl="true"  
        android:theme="@style/AppTheme">  
        《meta-data  
            android:name="design_width_in_dp"  
            android:value="360" />  
        《meta-data
            android:name="design_height_in_dp"  
            android:value="640" />  
       ...  
    </application》 
    
4.styles.xml文件下，主题换成无actionbar的例如：  
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">  
        <!-- Customize your theme here. -->  
    </style> 
5.log设置   在application中设置
        KLog.init(BuildConfig.LOG_DEBUG, "自定义");//log全局tag设置
         debug {
            buildConfigField "boolean", "LOG_DEBUG", "true"
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
        release {
            buildConfigField "boolean", "LOG_DEBUG", "false"
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
6.试着用吧  

    
