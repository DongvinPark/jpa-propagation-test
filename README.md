# JPA Transactional 어노테이션과 전파단계 테스트

## 테스트 목적 및 조건
- 부모 서비스 트랜잭션이 자식 서비스 트랜잭션을 내부적으로 호출하는 상황에서, Transactional 어노테이션이 지원하는 Required, Support, Requires_New, Nested 전파수준에 대해서 어디까지 롤백이 되는지를 알아보고자 테스트 했습니다.
- 아래의 모든 상황에 대하여 부모 엔티티 1개를 제거할 때 자식 엔티티 1개도 같이 제거되도록 비즈니스 로직을 구성했습니다.
- 첫 번째 상황은 부모 트랜잭션 내에서 자식 트랜잭션을 호출할 때, 부모트랜잭션이 자식트랜잭션을 포함하여 모든 DB I/O를 마치고나서 갑자기 런타임 예외를 던진 경우를 가정했습니다.
- 두 번째 상황은 부모 트랜잭션 중간에 호출되는 자식 트랜잭션에서 갑자기 런타임 예외를 던진 경우를 가정했습니다.
- 세 번째 상황은 전파 수준이 Required로 Default 값이고, Application 계층 서비스가 상호 독립적인 하위 자식 서비스 2개를 호출했는데 첫 번째로 호출한 자식 서비스가 런타임 예외를 던진 경우를 가정했습니다.
- 네 번째 상황은 전파 수준이 Required로 Default 값이고, Application 계층 서비스가 상호 독립적인 하위 자식 서비스 2개를 호출했는데 두 번째로 호출한 자식 서비스가 런타임 예외를 던진 경우를 가정했습니다.

## 테스트 결과
### 첫 번째 상황 : 부모 트랜잭션 실패
- required : 부모와 자식 트랜잭션 모두 롤백 됨.
- support : 둘 다 롤백 되지 않음.
- requires_new : 부모는 롤백 되었지만, 자식은 롤백되지 않음.
- nested : 부모와 자식 트랜잭션 모두 롤백 됨. 단, MySQL은 NESTED 옵션을 지원하지 않는다는 예외 발생.

### 두 번째 상황 : 자식 트랜잭션이 실패
- required : 부모와 자식 트랜잭션 모두 롤백 됨.
- support : 부모와 자식 트랜잭션 모두 롤백 됨.
- requires_new : 부모와 자식 트랜잭션 모두 롤백 됨.
- nested : 부모와 자식 트랜잭션 모두 롤백 됨. 단, MySQL은 NESTED 옵션을 지원하지 않는다는 예외 발생.

### 세 번째 상황 : Application 서비스가 하위 자식 서비스 2개 호출 후 첫 번째 자식 서비스가 실패(자식 서비스 간에는 서로 의존관계가 없으며, 모든 트랜잭션 전파 수준은 Required 임) 
- required : 부모와 자식 트랜잭션 모두 롤백 됨.

### 네 번째 상황 : Application 서비스가 하위 자식 서비스 2개 호출 후 두 번째 자식 서비스가 실패(자식 서비스 간에는 서로 의존관계가 없으며, 모든 트랜잭션 전파 수준은 Required 임)
- required : 부모와 자식 트랜잭션 모두 롤백 됨.


## 결과 이미지 모음
### 부모 트랜잭션 실패 with Propagation - required 
![40B6F4F6-64AC-4F2A-9841-8B1AF3FB95C4](https://user-images.githubusercontent.com/99060708/221344943-13a24ac7-f170-423d-9340-513661565926.jpeg)
![E78C44E9-F1E7-4EF3-A542-3352B211EE42](https://user-images.githubusercontent.com/99060708/221344946-3cb09a20-ad99-4c97-befb-5604c1806f98.jpeg)
### 부모 트랜잭션 실패 with Propagation - support
![C34E5AD3-66AE-4560-AFB6-524E11853476](https://user-images.githubusercontent.com/99060708/221345293-6db07fd7-f544-404c-96c1-45b9cbb585da.jpeg)
![ACD35930-A49F-40BF-9491-5B541703A1CD](https://user-images.githubusercontent.com/99060708/221345302-7a7d4d79-3ffd-450c-9a47-24c9426b4bc8.jpeg)
### 부모 트랜잭션 실패 with Propagation - requires_new
![16AEE40D-D797-4153-AD87-D2B11204AF07](https://user-images.githubusercontent.com/99060708/221345335-092d3dcb-4fd2-436c-a1ab-e23615ae478b.jpeg)
![C9B1FA82-74A8-40B2-8710-897639CF9726](https://user-images.githubusercontent.com/99060708/221345338-bd7558c7-56d8-4bbc-9741-d99652e93161.jpeg)
### 부모 트랜잭션 실패 with Propagation - nested
![3C760382-5C52-44CE-A6B0-E875CA037D51](https://user-images.githubusercontent.com/99060708/221345358-e3ba451c-c7ed-42f6-819e-bb069e12de81.jpeg)
![B05844E5-A789-4639-9929-B9926FC33EF6](https://user-images.githubusercontent.com/99060708/221345355-fc05b479-3c89-48ea-905b-08ddcae9918c.jpeg)
### 자식 트랜잭션 실패 with Propagation - required
![B85FC48A-85AC-4721-AD9D-846CA8561B1B](https://user-images.githubusercontent.com/99060708/221345379-4031a678-6d3e-4622-9d13-714b18f3d2b7.jpeg)
![8A4035BD-F6B5-49D6-AAD2-8C35FCBEC166](https://user-images.githubusercontent.com/99060708/221345382-479b1c75-8186-4220-95f8-fbfef1e8553c.jpeg)
### 자식 트랜잭션 실패 with Propagation - support
![62FBC340-55F6-4E6D-9223-CCAD07B53C61](https://user-images.githubusercontent.com/99060708/221345416-e5e639af-f5c6-4616-8448-162a63c00813.jpeg)
![571E7B35-35A3-4F54-9EE2-26F8EAF01C4F](https://user-images.githubusercontent.com/99060708/221345415-cc5441d1-42bc-42b8-b0c7-a95ad5bcae11.jpeg)
### 자식 트랜잭션 실패 with Propagation - requires_new
![EF488E25-EEB7-492B-9B96-AE32F8BA4F7D](https://user-images.githubusercontent.com/99060708/221345427-f3d38b76-5dc1-4693-ae3b-95d8bc8ef53f.jpeg)
![48B8F40E-CC86-4C8B-80E2-87BA114786A4](https://user-images.githubusercontent.com/99060708/221345431-8f52f371-4be1-4b93-8f34-3233ca8534b5.jpeg)
### 자식 트랜잭션 실패 with Propagation - nested
![7F93A9F3-42B5-4B82-8EDA-127B9D8F7CB2](https://user-images.githubusercontent.com/99060708/221345444-dbf5ca8e-73ee-4482-94d2-4876faf7809e.jpeg)
![B47E8FA8-2FC4-4990-8B71-8D1E24E245F9](https://user-images.githubusercontent.com/99060708/221345452-4b1ad926-062b-4586-b4fb-d931d6a0a41c.jpeg)
### Application 서비스 내의 첫 번째 자식 트랜잭션 with Propagation - required
![8B3422DE-025C-49D6-B219-5A58B2620D4E](https://user-images.githubusercontent.com/99060708/221345468-d950c895-27f9-42d9-b34b-30135c23f685.jpeg)
![D1076128-0B92-4DBC-8674-A1D5954D22A7](https://user-images.githubusercontent.com/99060708/221345470-2ff1056f-bc3f-469f-b17a-92685bf30cdd.jpeg)
### Application 서비스 내의 두 번째 자식 트랜잭션 with Propagation - required
![B44E2679-B343-46C2-BE33-0D330103503D](https://user-images.githubusercontent.com/99060708/221345481-40c22b1d-b8ab-4eb0-b281-44302b789e73.jpeg)
![B2EF913D-7BC6-4883-BCF6-FCFBEA28B9FB](https://user-images.githubusercontent.com/99060708/221345489-dccd8f16-096b-45ad-be54-e0e0e1fc2e21.jpeg)
