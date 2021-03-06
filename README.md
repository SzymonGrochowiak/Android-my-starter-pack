# Android project - my quick starter pack

It's a starter pack for creating Android projects in Android Studio. This collection contains libraries which I used to including to almost every project.

# Quick introduction

Since every project can have different configuration of libraries I create a sample project that presents a combination of my favourite libraries. The reasons why I created this is to:
* keep update with the newest versions of the libraries that I use
* create my own combination of the solutions
* have a playground for testing new libraries and tools and checking how they behave with my current solutions
* provide a sample Android application as a scheme for new projects

# Libraries
## Basic
| Name | Project site | Version |
|---|---|---|
| Dagger 2 | http://google.github.io/dagger/ |<a href='http://search.maven.org/#search%7Cga%7C1%7Cdagger'><img src='https://img.shields.io/maven-central/v/com.google.dagger/dagger.svg?maxAge=28800'></a>|
| Mosby MVP | https://github.com/sockeqwe/mosby |<a href='http://search.maven.org/#search%7Cga%7C1%7Cmosby'><img src='https://img.shields.io/maven-central/v/com.hannesdorfmann.mosby3/mvp.svg?maxAge=28800'></a>|
| Retrolambda | https://github.com/evant/gradle-retrolambda | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22me.tatarka%22%20AND%20a%3A%22gradle-retrolambda%22'><img src='https://img.shields.io/maven-central/v/me.tatarka/gradle-retrolambda.svg?maxAge=28800'></a> |
| RxJava2 | https://github.com/ReactiveX/RxJava | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava2%22%20AND%20a%3A%22rxjava%22'><img src='https://img.shields.io/maven-central/v/io.reactivex.rxjava2/rxjava.svg?maxAge=28800'></a> |
| RxAndroid2 | https://github.com/ReactiveX/RxAndroid | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava2%22%20AND%20a%3A%22rxandroid%22'><img src='https://img.shields.io/maven-central/v/io.reactivex.rxjava2/rxandroid.svg?maxAge=28800'></a> |
| Retrofit | http://square.github.io/retrofit/ | <a href='http://search.maven.org/#search%7Cga%7C1%7Cretrofit'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/retrofit.svg?maxAge=28800'></a> |
| Retrofit: GSON converter | - | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.retrofit2%22%20AND%20a%3A%22converter-gson%22'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/converter-gson.svg?maxAge=28800'></a> |
| Retrofit: RxJava2 adapter | - | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.retrofit2%22%20AND%20a%3A%22adapter-rxjava2%22'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/adapter-rxjava2.svg?maxAge=28800'></a> |
| OkHttp | http://square.github.io/okhttp/ | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.okhttp3%22%20'><img src='https://img.shields.io/maven-central/v/com.squareup.okhttp3/okhttp.svg?maxAge=28800'></a> |
| OkHttp: Logger | - | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.okhttp3%22%20AND%20a%3A%22logging-interceptor%22'><img src='https://img.shields.io/maven-central/v/com.squareup.okhttp3/logging-interceptor.svg?maxAge=28800'></a> |
| GSON | https://github.com/google/gson | <a href='http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.google.code.gson%22%20AND%20a%3A%22gson%22'><img src='https://img.shields.io/maven-central/v/com.google.code.gson/gson.svg?maxAge=28800'></a> |
| Timber | https://github.com/JakeWharton/timber | <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.jakewharton.timber%22'><img src='https://img.shields.io/maven-central/v/com.jakewharton.timber/timber.svg?maxAge=28800'></a> |
| Butterknife | http://jakewharton.github.io/butterknife/ | <a href='http://search.maven.org/#search%7Cga%7C1%7Cbutterknife'><img src='https://img.shields.io/maven-central/v/com.jakewharton/butterknife.svg?maxAge=28800'></a> |

## Test
| Name | Project site | Version |
|---|---|---|
| JUnit 4 | https://github.com/junit-team/junit4 |<a href='https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22junit%22%20AND%20a%3A%22junit%22'><img src='https://img.shields.io/maven-central/v/junit/junit.svg?maxAge=28800'></a>|
| Mockito | https://github.com/mockito/mockito |<a href='https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.mockito%22'><img src='https://img.shields.io/maven-central/v/org.mockito/mockito-core.svg?maxAge=28800'></a>|
| Dagger Mock | https://github.com/fabioCollini/DaggerMock |<a href='https://jitpack.io/#com.github.fabioCollini/DaggerMock'><img src='https://jitpack.io/v/com.github.fabioCollini/DaggerMock.svg'></a>|

# Features
- MVP architecture based on Mosby.
- Presenters that survive configuration changes.
- Dagger 2 dependency injection.
- Separated Dagger modules, scopes for presenters.
- Repository pattern for data managing.
- Local data storage: Realm.
- Handling no internet connection issue. Loading data from local storage.
- Unit tests.

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
