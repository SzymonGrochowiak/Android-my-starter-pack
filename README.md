# Android project - my quick starter pack

It's a starter pack for creating Android projects in Android Studio. This collection contains libraries which I used to including to almost every project.

# Quick introduction

As far as everybody knows there are many collections and sets that are available like [Code Path must have libraries] (https://github.com/codepath/android_guides/wiki/Must-Have-Libraries). Since every project can have different configuration of these libraries and their alternatives I created a sample where is presented a combination of my favourite libraries. The reasons why I created this is to:
* keep update with the newest versions of the libraries that I use
* create my own combination of the solutions
* have a playground for testing new libraries and tools and check how they behave with my current solutions
* provide a sample Android application as a scheme for new projects

# Libraries

## [Dagger 2] (https://github.com/google/dagger) <a href='http://search.maven.org/#search%7Cga%7C1%7Cdagger'><img src='https://img.shields.io/maven-central/v/com.google.dagger/dagger.svg?maxAge=28800'></a>


## [Google Play Services] (https://developers.google.com/android/guides/setup)

### Full dependency:
```groovy
compile 'com.google.android.gms:play-services:9.2.1'
```

### Separeted dependencies: https://developers.google.com/android/guides/setup

## Support libraries
```groovy
compile 'com.android.support:appcompat-v7:24.1.1'
compile 'com.android.support:cardview-v7:24.1.1'
compile 'com.android.support:design:24.1.1'
compile 'com.android.support:gridlayout-v7:24.1.1'
compile 'com.android.support:leanback-v17:24.1.1'
compile 'com.android.support:mediarouter-v7:24.1.1'
compile 'com.android.support:palette-v7:24.1.1'
compile 'com.android.support:recyclerview-v7:24.1.1'
compile 'com.android.support:support-annotations:24.1.1'
compile 'com.android.support:support-v13:24.1.1'
compile 'com.android.support:support-v4:24.1.1'
```

## [Multidex] (https://developer.android.com/studio/build/multidex.html#mdex-gradle)
```groovy
'compile com.android.support:multidex:1.0.1'
```

## Retrolambda

Will be updated soon. Need to check if it can be easily replaced by the new Java 8 integration system without conflicts with Dagger 2.

## RxJava

### Core:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex%22%20AND%20a%3A%22rxjava%22'><img src='https://img.shields.io/maven-central/v/io.reactivex/rxjava.svg?maxAge=28800'></a>

### RxAndroid:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex%22%20AND%20a%3A%22rxandroid%22'><img src='https://img.shields.io/maven-central/v/io.reactivex/rxandroid.svg?maxAge=28800'></a>

### [RxJavaDebug] (https://github.com/ReactiveX/RxJavaDebug)

### [RxBinding] (https://github.com/JakeWharton/RxBinding):
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.jakewharton.rxbinding%22'><img src='https://img.shields.io/maven-central/v/com.jakewharton.rxbinding/rxbinding.svg?maxAge=28800'></a>

## [Retrofit] (http://square.github.io/retrofit/)

### Core:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cretrofit'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/retrofit.svg?maxAge=28800'></a>

### JSON converter - GSON:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.retrofit2%22%20AND%20a%3A%22converter-gson%22'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/converter-gson.svg?maxAge=28800'></a>

### XML converter - Simple XML:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.retrofit2%22%20AND%20a%3A%22converter-simplexml%22'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/converter-simplexml.svg?maxAge=28800'></a>

### RXJava adapter:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.retrofit2%22%20AND%20a%3A%22adapter-rxjava%22'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/adapter-rxjava.svg?maxAge=28800'></a>

## [OkHttp] (http://square.github.io/okhttp/)

### Core:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.okhttp3%22%20'><img src='https://img.shields.io/maven-central/v/com.squareup.okhttp3/okhttp.svg?maxAge=28800'></a>

### Http Logger:
<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.okhttp3%22%20AND%20a%3A%22logging-interceptor%22'><img src='https://img.shields.io/maven-central/v/com.squareup.okhttp3/logging-interceptor.svg?maxAge=28800'></a>

## [Parceler] (http://parceler.org/)

<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.parceler%22'><img src='https://img.shields.io/maven-central/v/org.parceler/parceler.svg?maxAge=28800'></a>

## [Otto Event Bus] (http://square.github.io/otto/)

<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup%22%20AND%20a%3A%22otto%22'><img src='https://img.shields.io/maven-central/v/com.squareup/otto.svg?maxAge=28800'></a>

## [Timber] (https://github.com/JakeWharton/timber)

<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.jakewharton.timber%22'><img src='https://img.shields.io/maven-central/v/com.jakewharton.timber/timber.svg?maxAge=28800'></a>

## Pretty Logger

Will be updated soon

## [Glide] (https://github.com/bumptech/glide)

<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.bumptech.glide%22%20AND%20a%3A%22glide%22'><img src='https://img.shields.io/maven-central/v/com.github.bumptech.glide/glide.svg?maxAge=28800'></a>

## [Butterknife] (http://jakewharton.github.io/butterknife/)

<a href='http://search.maven.org/#search%7Cga%7C1%7Cbutterknife'><img src='https://img.shields.io/maven-central/v/com.jakewharton/butterknife.svg?maxAge=28800'></a>

## [LeakCanary] (https://github.com/square/leakcanary)

<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.leakcanary%22%20AND%20a%3A%22leakcanary-android%22'><img src='https://img.shields.io/maven-central/v/com.squareup.leakcanary/leakcanary-android.svg?maxAge=28800'></a>

## [Blockanary] (https://github.com/markzhai/AndroidPerformanceMonitor)

<a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.moduth%22'><img src='https://img.shields.io/maven-central/v/com.github.moduth/blockcanary-android.svg?maxAge=28800'></a>


# License

```
Copyright 2016 Szymon Grochowiak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
