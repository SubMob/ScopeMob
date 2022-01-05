# ScopeMob

![badge][badge-android]
![badge][badge-ios]
![badge][badge-js]
![badge][badge-jvm]

[![ScopeMob CI](https://github.com/SubMob/ScopeMob/actions/workflows/main.yml/badge.svg)](https://github.com/SubMob/ScopeMob/actions/workflows/main.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.submob/scopemob/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.submob/scopemob)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/35c32a0221ab44e18400834c35b8f402)](https://www.codacy.com/gh/SubMob/ScopeMob?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=SubMob/ScopeMob&amp;utm_campaign=Badge_Grade)
[![Codacy Coverage](https://app.codacy.com/project/badge/Coverage/35c32a0221ab44e18400834c35b8f402)](https://www.codacy.com/gh/SubMob/ScopeMob/dashboard?utm_source=github.com&utm_medium=referral&utm_content=SubMob/ScopeMob&utm_campaign=Badge_Coverage)
[![Codecov Badge](https://codecov.io/gh/SubMob/ScopeMob/branch/master/graph/badge.svg?token=MPEA1FBVT3)](https://codecov.io/gh/SubMob/ScopeMob)

## Install

ScopeMob is currently published to Maven Central, so add that to repositories.

```groovy
repositories {
    mavenCentral()
}
```

Then, simply add the dependency to your common source-set dependencies

```groovy
commonMain {
    dependencies {
        implementation("com.github.submob:scopemob:LATEST_VERSION")
    }
}
```

## Documentation

<details>
<summary>whether/whetherNot</summary>
<br>

`whether` and `whetherNot` takes all the lambda parameters and applies `and`/`&&` operator. `whether` returns the caller object to `let` if the result is `true` otherwise it returns `false` so `?:run` blocks will run.

`whetherNot` does the same but when the result is `false`.

```kotlin
SomeObject
    ?.whether(
        { it.someBoolean }, // `it` is SomeObject
        { this.someBoolean }, // `this` is some object, you can also use simply someBoolean
        { someOtherBoolean }
    )?.let {
        // runs if all the conditions are true
    } ?: run {
    // runs if 
}

// Syntax with one argument
SomeObject
    ?.whether { it.someBoolean } // `it` is SomeObject
    ?.whether { this.someBoolean } // `this` is some object, you can also use simply someBoolean
    ?.whether { someOtherBoolean }
    ?.let {
        // runs if all the conditions are true
    } ?: run {
    // runs if 
}
```

</details>

<details>
<summary>either/eitherNot</summary>
<br>

`either` and `eitherNot` takes all the lambda parameters and applies `or`/`||` operator. `either` returns the caller object to `let` if the result is `true` otherwise it returns `false` so `?:run` blocks will run.

`eitherNot` does the same but when the result is `false`.

```kotlin
SomeObject
    ?.either(
        { it.someBoolean }, // `it` is SomeObject
        { this.someBoolean }, // `this` is some object, you can also use simply someBoolean
        { someOtherBoolean }
    )?.let {
        // runs if all the conditions are true
    } ?: run {
    // runs if 
}
```

</details>

<details>
<summary>ensure</summary>
<br>

`ensure` takes many variables as argument and let the block work only if all the variables are not null.

```kotlin
ensure(variable1, variable2, variable3, ...) {
    // The block run only if all the variables are not null
}
```

</details>

### License

```text
Copyright 2020 Mustafa Ozhan

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[badge-android]: https://img.shields.io/badge/platform-android-green

[badge-ios]: https://img.shields.io/badge/platform-ios-orange

[badge-js]: https://img.shields.io/badge/platform-js-yellow

[badge-jvm]: https://img.shields.io/badge/platform-jvm-red