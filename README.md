# Google Colud Bigquery

## Dependency

#### Maven
```xml
<!--  Using libraries-bom to manage versions.
See https://github.com/GoogleCloudPlatform/cloud-opensource-java/wiki/The-Google-Cloud-Platform-Libraries-BOM -->
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.google.cloud</groupId>
      <artifactId>libraries-bom</artifactId>
      <version>16.1.0</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>com.google.cloud</groupId>
    <artifactId>google-cloud-bigquery</artifactId>
  </dependency>
</dependencies>
```

#### Gradle
```groovy
compile 'com.google.cloud:google-cloud-bigquery:1.124.7'
```

## Credentials
1. 서비스 계정 생성
```
gcloud iam service-accounts create NAME
```
2. 권한 부여
```groovy
gcloud projects add-iam-policy-binding PROJECT_ID
```

Ref. [리소스에 대한 액세스 권한 부여, 변경, 취소](https://cloud.google.com/iam/docs/granting-changing-revoking-access)

3. 키 파일 생성
```
gcloud iam service-accounts keys create FILE_NAME.json --iam-account=NAME@PROJECT_ID.iam.gserviceaccount.com
```

## Credentials 적용

#### Linux
```groovy
export GOOGLE_APPLICATION_CREDENTIALS="[PATH]"
```

#### Windows
PowerShell
```groovy
$env:GOOGLE_APPLICATION_CREDENTIALS="[PATH]"
```
CMD
```groovy
set GOOGLE_APPLICATION_CREDENTIALS=[PATH]
```