# beans 패키지

이 패키지는 자바빈을 생성하기 위한 인터페이스와 클래스들을 포함한다
다른 스프링 패키지들에서 가장 많이 사용하는 패키지일 것이다

--- 

하위패키지
    factory
    propertyeditors
    support

---

- BeansException : 빈, 서브패키지에서의 던져지는 모든 예외들에 대한 추상클래스
- BeanWrapper : 스프링 로우레벨 자바빈즈 인프라스트럭쳐의 핵심 인터페이스
- BeanWrapperImpl : BeanWrapper 인터페이스의 의 기본구현체(중요)

- MutablePropertyValues : PropertyValues 에 대한 기본 구현체로 properties 에 대한 단순한 생성과 맵으로부터의 구성 딥카피를 지원하기 위한 생성자의 제공을 허용합니다.
- PropertyValue : 각각의 속성들에 대한 정보와 값을 가지기 위한 클래스

- (I)PropertyValues : 0 혹은 많은 PropertyValue 들을 간직하는 객체
- ProperyValuesEditor : PropertyValues 를 위한 에디터로 GUI 에디터가 아니다