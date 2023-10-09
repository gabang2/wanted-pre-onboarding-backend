# 요구사항 분석
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
## 데이터베이스 설계
![image](https://github.com/gabang2/wanted-pre-onboarding-backend/assets/82714785/df8aa4c0-46c5-4ba1-b086-39ceef0fc8d8)
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
