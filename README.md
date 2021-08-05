# demo

---home.html
![image](https://user-images.githubusercontent.com/71180644/128313294-1a9ae800-bb75-47ad-9189-6ede3d5aaac4.png)

---createAccountForm.html
![image](https://user-images.githubusercontent.com/71180644/128313233-ca27148e-9117-4cf4-a430-4cc7512d012f.png)

---정상적으로 data 들어옴
![image](https://user-images.githubusercontent.com/71180644/128313545-cab07c76-e97a-4c65-98db-ccf421b7e850.png)

---로그인 페이지
![image](https://user-images.githubusercontent.com/71180644/128313627-eff3a6df-7e04-4383-86a9-9535eaae33df.png)

---메모장 메인 페이지
![image](https://user-images.githubusercontent.com/71180644/128313711-842e22f1-4ff8-4462-8874-f6ed2e88618d.png)

---글 쓰기 
![image](https://user-images.githubusercontent.com/71180644/128313795-205b1c97-1e41-45cf-8709-3b46dad382ea.png)
---글 로딩 된거 확인
![image](https://user-images.githubusercontent.com/71180644/128313853-fbaf6fe1-68c7-4864-9447-baacae9bbf1d.png)





spring boot mvc패턴을 적용한 간단한 로그인& 로그인시 메모장기능을 이용할수 있게 만들고 있습니다

회원가입시 jpa를 이용한 h2database에 저장되는거 확인, 로그인 부분 오류 수정중...

메모리스트 및 글 쓰기 기능 완료,

추가 기능 list(//생각이 좀 필요,,,,)

1.메모 수정기능

2.css(부트스트랩)

3.회원 메모장 연관관계를 이용해 로그인후 작성한 본인 메모작성 내역만 확인 가능


jpa상으로

Member <-> Board

1:N 연관관계로

각각의 Repository에em.persist(); 를 이용해 데이터 저장 후

em.find()를 이용한 데이터 값 찾기...

수정은 -------------------------------

@setter을 금지한다면

member.ChangeAge();

를 이용해 영속성컨텍스트에서 변경감지를 통해 데이터 수정 가능

em.persist();

해야함.마지막엔...


삭제는--------------------------------

em.remove();

하면됩니다!

다만 mvc패턴에 맞게

view는 아주 간단한 기능만 동작하게 구현하고

JpaRepository를 상속받아 구현하고

service는 controller에서 사용할 기능을 메소드처럼 구현해 두고

controller에서 restAPI를 이요한 get set mapping을 이용해 view data전송시 model을 이용해서 data전송

API설계만 하면 된다는 가정에 json으로 반환하면

@RestController

@RequestBody

를 이용해

public String 메소드명(){
}

전송해 준다..

---------------------------
다만 아직 어렵다... 고로 지속적으로 수정해 보겠습니다... 하던공부 하면서



