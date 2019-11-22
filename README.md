# mvpCommon/n
mvp架构基础库/n
1.build.gradle(project)添加/n
repositories {/n
        maven { url 'https://jitpack.io' }/n
        .../n
    }/n
2.build.gradle(app)添加  /n
dependencies {/n
    .../n
    implementation 'com.github.wutianchi:mvpCommon:v1.0'/n
}/n
3.AndroidManifest.xml添加 其中360和640为设计出图的规格，需要根据自己实际的UI图更改/n
    <application/n
        android:allowBackup="true"/n
        android:icon="@mipmap/ic_launcher"/n
        android:label="@string/app_name"/n
        android:roundIcon="@mipmap/ic_launcher_round"/n
        android:supportsRtl="true"/n
        android:theme="@style/AppTheme">/n
        <meta-data/n
            android:name="design_width_in_dp"/n
            android:value="360" />/n
        <meta-data
            android:name="design_height_in_dp"/n
            android:value="640" />/n
       .../n
    </application>/n
4.styles.xml文件下，主题换成无actionbar的例如：/n
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">/n
        <!-- Customize your theme here. -->/n
    </style>/n
5.试着用吧/n

    
