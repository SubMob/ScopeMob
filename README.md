# scopemob

![badge][badge-android]
![badge][badge-ios]
![badge][badge-js]
![badge][badge-jvm]

[![master](https://github.com/SubMob/ScopeMob/actions/workflows/master.yml/badge.svg)](https://github.com/SubMob/ScopeMob/actions/workflows/master.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.sub-mob/scopemob/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.sub-mob/scopemob)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/35c32a0221ab44e18400834c35b8f402)](https://www.codacy.com/gh/SubMob/ScopeMob?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=SubMob/ScopeMob&amp;utm_campaign=Badge_Grade)

## Install

Multiplatform Settings is currently published to Maven Central, so add that to repositories.

```groovy
repositories {
    mavenCentral()
}
```

Then, simply add the dependency to your common source-set dependencies

```groovy
commonMain {
    dependencies {
        implementation("com.github.sub-mob:scopemob:LATEST_VERSION")
    }
}
```

### License

```markdown
Copyright 2020 Mustafa Ozhan

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

[badge-android]: https://img.shields.io/badge/platform-android-green

[badge-ios]: https://img.shields.io/badge/platform-ios-orange

[badge-js]: https://img.shields.io/badge/platform-js-yellow

[badge-jvm]: https://img.shields.io/badge/platform-jvm-red