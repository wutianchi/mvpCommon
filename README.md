# mvpCommon/n
mvp架构基础库  
1.build.gradle(project)添加  
repositories {  
        maven { url 'https://jitpack.io' }  
        ...  
    }  
2.build.gradle(app)添加   
dependencies {  
    ...  
    implementation 'com.github.wutianchi:mvpCommon:v1.0'  
}  
3.AndroidManifest.xml添加 其中360和640为设计出图的规格，需要根据自己实际的UI图更改  
    <application  
        android:allowBackup="true"  
        android:icon="@mipmap/ic_launcher"  
        android:label="@string/app_name"  
        android:roundIcon="@mipmap/ic_launcher_round"  
        android:supportsRtl="true"  
        android:theme="@style/AppTheme">  
        <meta-data  
            android:name="design_width_in_dp"  
            android:value="360" />  
        <meta-data
            android:name="design_height_in_dp"  
            android:value="640" />  
       ...  
    </application>  
4.styles.xml文件下，主题换成无actionbar的例如：  
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">  
        <!-- Customize your theme here. -->  
    </style>  
5.试着用吧  

    
