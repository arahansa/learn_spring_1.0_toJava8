
# core 패키지

예외 처리를 가능하게 하고, 프레임워크에 속하지 않는 핵심 인터페이스들을 제공하는 기본적인 클래스

--- 

- ConstantException : Constants 클래스가 유효하지 않은 상수 이름을 물어볼때 발생할 수 있는 예외
- Constants : 상수정의를 가진 다른 클래스들을 파싱하는 데 사용될 수 있다
- (I) ControlFlow : 현재 콜스택에 대한 정보를 리턴하는 오브젝트에 의해 구현될 인터페이스
- ControlFlowFactory : 자바 1.4 나 1.3인지 선택하기 위한 싱글턴 팩토리
- (I) ErrorCoded : 예외에 의해 구현될 인터페이스로 에러코드는 문자열, 숫자가 되어서 유저가 읽을 수 있는 값같은 것으로 변환될 것이다.

- JdkVersion : 현재 JVM version 을 찾기 위해 사용되는 클래스
- NestedCheckedException : 체크예외를 랩핑하기 위한 편의성 클래스
- NestedRuntimeException : 런타임예외를 랩핑하기 위한 편의성 클래스
- OrderComparator : 순서있는 객체들을 위한 비교구현체
- (I) Ordered { getOrder() } : 순서가능한 오프젝트가 구현해야하는 인터페이스 예를 들자면 콜렉션 같은

## 보유 패키지
- io : 입출력에 관련된 패키지