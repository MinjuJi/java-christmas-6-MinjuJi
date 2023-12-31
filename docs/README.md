# 미션 - 크리스마스 프로모션

---

## 구현 기능 목록

### 입력 기능

- 예상 방문 날짜 입력 기능
- 주문 메뉴와 개수 입력 기능
    - 구분 기호를 인자로 받기

### 계산 기능

- 고객이 입력한 일수가 평일인지 주말인지 계산하는 기능
- 크리스마스 디데이 할인 계산 기능
    - 25일까지 할인
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
- 평일 할인 계산 기능
    - 31일까지 할인
    - 평일(일요일~목요일)에는 디저트 메뉴 1개당 2,023원 할인
        - 고객이 예약한 디저트 메뉴의 개수를 반환하는 기능
- 주말 할인 계산 기능
    - 31일까지 할인
    - 주말(금요일, 토요일)에는 메인 메뉴 1개당 2,023원 할인
        - 고객이 예약한 메인 메뉴의 개수를 반환하는 기능
- 특별 할인 계산 기능
    - 31일까지 할인
    - 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
        - 12월의 일요일과 25일에 별표시
- 할인 전 총주문 금액을 계산하는 기능
- 샴페인 1개 증정 기능
    - 31일까지 증정
    - 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 베네핏 클래스의 인스턴스를 초기화하는 기능
- 총 혜택 금액을 계산하는 기능
    - 할인 금액의 합계 + 증정 메뉴의 가격
- 총혜택 금액에 따라 다른 이벤트 배지를 부여하는 기능
    - 5천 원 이상: 별
    - 1만 원 이상: 트리
    - 2만 원 이상: 산타
- 할인 후 예상 결제 금액을 계산하는 기능
    - 할인 전 총주문 금액 - 할인 금액(증정 메뉴 제외)

### 출력 기능

- 인사 메시지 출력 기능
- 예약 날짜에 해당하는 이벤트 혜택 미리 보기 메시지 출력 기능
- 예약한 주문 메뉴 출력 기능
- 할인 전 총주문 금액 출력 기능
- 증정 메뉴 출력 기능
    - 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"으로 출력
- 혜택 내역 출력 기능
    - 적용된 이벤트가 하나도 없다면 혜택 내역 "없음"으로 출력
- 총혜택 금액 출력 기능
- 할인 후 예상 결제 금액 출력 기능
- 12월 이벤트 배지 출력 기능
    - 이벤트 배지가 부여되지 않는 경우, "없음"으로 출력

### 제한 기능

- 할인 전 총주문 금액이 10,000원 이상일 때 이벤트가 적용되도록 하는 기능

### 예외 검사 기능

- 방문할 날짜가 1 이상 31 이하의 숫자인지 검사
    - 1 이상 31 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지 출력
- 예약한 주문이 메뉴판에 있는 메뉴인지 검사
    - 메뉴판에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지 출력
- 주문 메뉴의 총 개수가 1 이상 20 이하의 숫자인지 검사
    - 1 이상 20 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지 출력
- 유효한 메뉴 형식인지 검사
    - 메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지 출력
- 중복 메뉴를 입력했는지 검사
    - 중복 메뉴를 입력한 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지 출력
- 음료만 주문되었는지 검사
    - 음료만 입력한 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지 출력