# 원티드 프리온보딩 사전과제(10월)
- 이름 : 김가영
## 어플리케이션 실행 방법
1. git clone
   ```shell
   git clone https://github.com/gabang2/wanted-pre-onboarding-backend.git
   ```
2. database 설정 - application.yml파일 수정
   ```shell
   url: jdbc:mysql://localhost:3306/{스키마 이름}
   username: {db의 user 이름}
   password: {db의 password}
   ```
## 데이터베이스 테이블 구조
![image](https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/1229afb7-5471-48d6-9efa-70b034329ecf)

## API 명세

| NO. | Description | Method | Path                      | Request Parameters                                                      |
|:---:|-------------|--------|---------------------------|-------------------------------------------------------------------------|
|  1  | 회사 등록       | POST   | /companys                 | `body`  { name }                                                        |
|  2  | 사용자 등록      | POST   | /users                    | `body`  { name }                                                        |
|  3  | 채용공고 등록     | POST   | /recruits                 | `body`  { companyId, country, region, position, reward, tech, content } |
|  4  | 채용공고 수정     | PATCH  | /recruits/{recruit-id}    | `body`  { country, region, position, reward, tech, content }            |
|  5  | 채용공고 삭제     | DELETE | /recruits/{recruit-id}    |                                                                         |
|  6  | 채용공고 목록 조회  | GET    | /recruits?search={search} | `query` search <br>(optional)                                           |
|  7  | 채용공고 상세 조회  | GET    | /recruits/{recruit-id}    |                                                                         |

## api 명세서
1. [채용공고 등록] **POST** /recruitments
   - request
       ```json
         {
            "id": 3,
            "companyName": "companynametest",
            "country": "한국",
            "region": "서울",
            "position": "Django 백엔드 개발자",
            "reward": 100000,
            "tech": "Django",
            "content": "경력 1년 이상 Django 개발자를 모십니다."
         }
       ```
   - response **201 created**
      ```
          {
            "id": 3,
            "companyName": "companynametest",
            "country": "한국",
            "region": "서울",
            "position": "Django 백엔드 개발자",
            "reward": 100000,
            "tech": "Django",
            "content": "경력 1년 이상 Django 개발자를 모십니다."
          }
      ```
         

2. [채용공고 수정] **PATCH** /recruitments/{recruitments-id}
   - request : 회사 pk를 제외한 모든 것을 변경할 수 있음
       ```json
         {
           "country" : "한국",
           "region" : "전주",
           "position" : "Django 백엔드 개발자",
           "reward" : 100000,
           "tech" : "Django",
           "content" : "경력 1년 이상 Django 개발자를 모십니다."
         }
       ```
  
   - response **200** OK
       ```json
         {
            "id": 3,
            "companyName": "companynametest",
            "country": "한국",
            "region": "전주",
            "position": "Django 백엔드 개발자",
            "reward": 100000,
            "tech": "Django",
            "content": "경력 1년 이상 Django 개발자를 모십니다."
         }
       ```
  
3. [채용공고 삭제] **DELETE** /recruitments/{recruitment-id}
    - response **204** No Content
4. [채용공고 조회(모두)] **GET** /recruitments
    - response **200** OK
      ```json
         [
            {
               "id": 2,
               "companyName": "companynametest",
               "country": "한국",
               "region": "서울",
               "position": "Django 백엔드 개발자",
               "reward": 100000,
               "tech": "Django",
               "content": "경력 1년 이상 Django 개발자를 모십니다."
            },
            {
               "id": 3,
               "companyName": "companynametest",
               "country": "한국",
               "region": "전주",
               "position": "Django 백엔드 개발자",
               "reward": 100000,
               "tech": "Django",
               "content": "경력 1년 이상 Django 개발자를 모십니다."
            }
         ]
      ```
5. [채용공고 조회(검색)] **GET** /recruitments?search={search}
   - response **200** OK : 내용 중 search와 일치하는 내용이 컬럼 상관 없이 있으면 해당하는 목록을 반환
      ```json
      search에 "전주"를 검색한 상태 
     [
         {
         "id": 3,
         "companyName": "companynametest",
         "country": "한국",
         "region": "전주",
         "position": "Django 백엔드 개발자",
         "reward": 100000,
         "tech": "Django",
         "content": "경력 1년 이상 Django 개발자를 모십니다."
         }
      ]
     ```
   
6. [채용 상세 페이지] **GET** /recruitments/{recruitment-id}
    - response : 현재 채용 공고를 제외한, 회사의 채용공고 리스트 보여줌(anotherRecruit)
      ```json
      {
         "id": 2,
         "companyName": "companynametest",
         "country": "한국",
         "region": "서울",
         "position": "Django 백엔드 개발자",
         "reward": 100000,
         "tech": "Django",
         "content": "경력 1년 이상 Django 개발자를 모십니다.",
         "anotherRecruit": [
            3,
            4
         ]
      }
       ```
   
7. [채용 공고 지원] **POST** /user-recruits
    - request
      ```json
      {
         "userId" : 2,
         "recruitId" : 3
      }
      ```
    - resoponse **201** Created
      ```json
         {
            "user": {
            "id": 5,
            "name": "김가영"
            },
            "recruit": {
            "id": 3,
            "companyName": null,
            "country": "한국",
            "region": "전주",
            "position": "Django 백엔드 개발자",
            "reward": 100000,
            "tech": "Django",
            "content": "경력 1년 이상 Django 개발자를 모십니다."
            }
            }
      ```
      
## 별도의 api
1. [회사 등록] **POST** /companys
    - request
      ```json
         {
            "name" : "companynametest"
         }
       ```
   - response **201** Created
      ``` json
     {
        "id": 1,
        "name": "companynametest"
      }
     ```
2. [사용자 등록] **POST** /users
   - request
       ```json
       {
         "name" : "김가영"
       }
        ```
   - response **201** Created
      ```json
      {
        "id": 2,
        "name": "김가영"
      }
     ```

## 요구사항 분석
1. 채용 공고 등록
   - 회사 **[회사 id]** 는 채용 공고를 등록할 수 있다.
   - 채용 공고에는 **[국가, 지역, 채용포지션, 채용보상금, 사용기술, 채용내용]** 을 등록할 수 있다.
   - 모든 컬럼의 값은 필수로 입력해야 한다.
   - 채용보상금은 0보다 크거나 같아야 한다.
2. 채용 공고 수정
   - **[국가, 지역, 채용포지션, 채용보상금, 사용기술, 채용내용]** 을 수정할 수 있다.
   - 채용보상금은 0보다 크거나 같아야 한다.
   - 입력하지 않는 경우에는 수정하지 않는다.
3. 채용 공고 삭제
   - 채용 공고를 삭제한다.
   - 채용공고를 등록한 회사와 채용공고에 지원한 사용자의 외래키 목록에서 삭제한다.
4. 채용 공고 조회
   - 단순 조회
      - 모든 채용공고 목록을 리스트 형식으로 조회한다.
      - 회사는 id가 아닌 회사명으로 표시한다.
   - 검색 조회
      - search-keyword를 기준으로 채용공고 목록을 리스트 형식으로 조회한다.
      - 모든 컬럼에서 search-keyword를 포함하고 있으면 목록에 포함한다.
      - 회사는 id가 아닌 회사명으로 표시한다.
5. 채용 상세페이지 조회
   - 채용 공고에서 회사가 올린 다른 채용 공고를 포함해서 응답한다.
   - 현재 확인하고 있는 채용 공고와 다른 채용공고의 id만 포함해서 응답한다.
6. 사용자의 채용 공고 지원
   - 사용자는 하나의 채용공고에 대해서 한 번만 지원 가능하다.
   - 사용자의 id와 채용 공고의 id를 이용하여 채용 공고에 지원한 정보를 저장한다.

## 구현 방법 및 이유
### 검색 기능
- 모든 컬럼에 대해서 검색을 할 수 있도록 JPQL 쿼리 작성
   ```java
   public interface RecruitRepository extends JpaRepository<Recruit, Long> {
       // 키워드 기준으로 검색
       @Query("select r from Recruit r "
               + "where r.company.name like concat('%', :search, '%')"
               + "or r.country like concat('%', :search, '%')"
               + "or r.region like concat('%', :search, '%')"
               + "or r.position like concat('%', :search, '%')"
               + "or r.tech like concat('%', :search, '%')"
               + "or r.content like concat('%', :search, '%')")
       List<Recruit> findBySearch(@Param("search") String search);
   
   }
   ```
  - datajpa의 jpql을 이용하여 복잡해질 수 있는 쿼리를 가시적으로 작성하였음
  - querydsl은 오히려 가독성이 떨어진다고 판단하였음

### 검증 메서드 분리
- verified(id) 메서드
   ```java
    @Transactional(readOnly = true)
    public Recruit verifiedRecruit(Long recruitId) {
        return recruitRepository.findById(recruitId).orElseThrow(() -> new RuntimeException("채용 공고 Id 가 유효하지 않습니다."));
    }
   ```
  - 엔티티의 id가 없는데 엔티티를 사용하려고 할 경우 exception을 throw 하도록 메서드를 만들었음
  - 중복되는 코드를 줄여 코드의 복잡도를 낮추고, 메서드의 재사용성을 높임.
- 한 유저가 채용 공고에 두 번 지원했을 경우, 에러 발생 Exception 메서드 분리
   ``` java
   private static void verifiedUserRecruit(User user, Recruit recruit) {
     for (UserRecruit userRecruit : user.getUserRecruits()) {
         if (recruit == userRecruit.getRecruit()) {
             throw new RuntimeException("한 번 지원한 공고는 두 번 지원할 수 없습니다.");
         }
     }
   }
  ```
  - Service단의 길어지는 코드를 분리하여 코드 가독성을 높임

### Response와 엔티티 매핑
- Mapstruct를 이용하여 엔티티를 Response와 매핑
   ```java
   RecruitResponseDto response = recruitMapper.recruitToRecruitResponseDTO(recruit);
   ```
- 불필요한 코드를 줄이기 위해 사용함
  
### Transactional(readonly=true)
- 불필요한 메모리 사용을 최소화하기 위해서 GET메서드 또는 verified엔티티 메서드는 readonly로 설정
   ```java
   @Transactional(readOnly = true)
   public User verifiedUser(Long userId) {
      return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("userId가 유효하지 않습니다."));
   }
   ```
  
## 시연영상

### 채용공고 등록
https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/09428f3e-1ecc-4132-a882-6dfe455bb0c3

### 채용공고 수정
https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/fd4b9054-f80c-4e9e-9ec0-473ed462775a

### 채용공고 삭제
https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/37e48626-3fac-4645-b107-80c688ba33d9

### 채용공고 목록 조회 + 검색
https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/d2052334-a914-4158-a76c-d5077f8dad6f

### 채용공고 상세 조회(단일)
https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/3110401d-c933-4b59-bc6f-e654b77ac057

### 채용공고 지원
https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/1e483a6f-0613-4e8f-9a19-d2e1a8cfba2b

## Git Convention
- Commit 규칙
```text
태그 #이슈번호 : commit 내용
```

- 태그 규칙
```text
Feat	    새로운 기능을 추가
Fix         버그 수정
Design	    CSS 등 사용자 UI 디자인 변경
!HOTFIX	    급하게 치명적인 버그를 고쳐야하는 경우
Style	    코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우
Refactor    프로덕션 코드 리팩토링
Comment	    필요한 주석 추가 및 변경
Docs	    문서 수정
Test	    테스트 코드, 리펙토링 테스트 코드 추가, Production Code(실제로 사용하는 코드) 변경 없음
Chore	    빌드 업무 수정, 패키지 매니저 수정, 패키지 관리자 구성 등 업데이트, Production Code 변경 없음
Rename	    파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우
Remove	    파일을 삭제하는 작업만 수행한 경우
```
