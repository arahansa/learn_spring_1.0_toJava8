
# core/io 패키지

Generic abstraction for (file-based) resources, used throughout the framework.

프레임워크내에서 사용되는 파일 기반 자원들을 위한 일반적인 추상화

---

- AbstractResource : 자원구현체와 일반적 행동을 미리 구현(pre-implementing) 하기 위한 편의 클래스
- ClassPathResource : 클래스 패스의 자원들을 위한 리소스 구현체
- DefaultResourceLoader : ResourceLoader 인터페이스를 위한 기본적 구현체로 ResourceEditor에 사용되며 또한 독립적인 사용에도 적합하다
- FileSystemResource : Java.io.File 을 다루기 위한 리소스 구현체로 File 에 대한 해석과 URL 까지 지원
- InputStreamResource : 주어진 인풋스트림에 대한 리소스 구현체. 어떠한 리소스 구현체도 해당하지 않은 경우에 사용?

- (I) InputStreamSource { getInputStream() }  : java.io.InputStreams 를 위한 소스 오브젝트에 대한 간단한 인터페이스로 Spring의 Resource 인터페이스에 대한 기반 인터페이스
- (I) Resource : 파일이나 클래스 패스같은 실제 resource 타입에 대한 추상층에서의 리소스 설명자들을 위한 인터페이스
- ResourceEditor : 리소스 설명자들을 위한 에디터로 문자열 위치를 리소스 속성으로 자동으로 변환해준다. ( ${user.dir} -> 실제 위치 변환을 얘기하는 듯?)
- (I) ResourceLoader { getResource } : resource들을 불러오는 객체에 의해 구현될 인터페이스로 ApplicationContext 는 이 기능을 제공해야 합니다
- UrlResource : java.net.URL 을 위한 리소스 구현체로 특별히 URL, 또한 file: 프로토콜을 위한 File 을 지원합니다