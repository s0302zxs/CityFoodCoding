# CityFoodCoding

## ScreenShots and Record


https://user-images.githubusercontent.com/25497010/156099277-c16b7154-2a7d-40df-b722-8c5766519b39.mp4


https://user-images.githubusercontent.com/25497010/156099293-6240b54e-5a78-46c7-9ab2-ddad07fbe827.mp4


![Screenshot_20220301-112039](https://user-images.githubusercontent.com/25497010/156099304-249a948e-c4dd-411a-a7b9-39ef9934a86a.png)


## Build Notice
if this error happened `Android Gradle plugin requires Java 11 to run. You are currently using Java 1.8.`
In Android STudio ,please go to `For Mac: Preferences → Build → Execution, Deployment → Build Tools → Gradle → Gradle JDK.` to change setting


## Function
1. Kotlin + MVVM + Repository Pattern + Coroutine + Coroutine Flow + Room + Retrofit
2. Error Handing : 
    1. add coroutine flow catch, if comes here(ex : no internet or timeout), show errorMessage and retry button
    2. When api load successful , will save data to DB
    3. When api load fail, will get data from DB
3. UI Spec : 
    1. Because there's no detail UI(Ex: size, layout), so I just watch the image to implement
    2. Simple Loading progressBar
4. Unit Test : 
    1. Room DB Test in androidTest
    2. ViewModel and repositoryTest in test
5. Pull-to-refresh
6. Navigate animation(Slide left and right)


