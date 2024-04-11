$(document).ready(function () {
    // 페이지 로드 시 각 항목의 총 가격 계산하여 표시
    calculateAndDisplayTotal();

    // 각 항목의 수량이 변경될 때마다 총 가격 업데이트
    $("input[type='number']").on('input', function () {
        calculateAndDisplayTotal();
    });
});

// 페이지 로드 시 각 항목의 총 가격 계산하여 표시하는 함수
function calculateAndDisplayTotal() {
    $("tbody tr").each(function () {
        let price = parseFloat($(this).find("td:nth-child(2)").text()); // 메뉴 가격
        let quantity = parseInt($(this).find("input[type='number']").val()); // 수량
        let total = price * quantity; // 총 가격 계산
        $(this).find("input[type='text']").val(total); // 총 가격 표시
    });
}
//장바구니에서 한줄삭제
function deleteRow(event){
        let row = event.target.closest("tr");
        let id = "aa1";
        let menuname = row.querySelector("td:nth-child(1)").textContent;
        let confirmy = confirm(menuname+"를 삭제하시겠습니까?")
        if(confirmy){
        window.location.href = "/deleteOne?orderid="+id+"&ordermenu="+menuname;
        }
}

//결제진행
function paymentCof(){
    const total = calculateTotal();
    const confirmation = confirm(total + " 원 결제 하시겠습니까?");
    const orderId = document.getElementById("orderId").value;
    alert(orderId);
    if(confirmation){
    IMP.init('imp31427128')
    IMP.request_pay(
              {
                // param
                pg: 'kakaopay.TC0ONETIME', // PG사 코드표에서 선택
                pay_method: 'card', // 결제 방식
                merchant_uid: makeMerchantUid(), // 결제 고유 번호
                name: `커피테스트`, // 제품명
                amount: total, // 가격
                //구매자 정보 ↓
                buyer_email: "alsqod89@naver.com",
                buyer_name: "홍길동",
                buyer_tel: "010-4242-4242",
                buyer_addr: "서울특별시 강남구 신사동",
                buyer_postcode: "01181",
              },
              async (rsp) => {
                // callback
                if (!rsp.success) {
                    alert("결제실패");
                    window.location.href = "/index";
                }else{
                    alert("결제성공");

                    const data ={
                        orderid: orderId,
                        orderno: merchant_uid

                    }fetch('/ordersuccess', {
                         method: 'POST',
                         headers: {
                             'Content-Type': 'application/json',
                         },
                         body: JSON.stringify(data),
                     })
                     .then(response => {
                         if (!response.ok) {
                             throw new Error('Network response was not ok');
                         }
                         return response.json();
                     })
                     .then(data => {
                         console.log('Success:', data);
                     })
                     .catch((error) => {
                         console.error('Error:', error);
                     });
                }
            );
          }
}

// yymmddhhmm 형식으로 현재 시각을 반환하는 함수
function makeMerchantUid() {
    const now = new Date();
    const year = now.getFullYear().toString().slice(2);
    const month = (now.getMonth() + 1).toString().padStart(2, '0');
    const day = now.getDate().toString().padStart(2, '0');
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    return 'IMP' + year + month + day + hours + minutes;
}

// 메뉴 합계
function calculateTotal() {
    const totals = document.querySelectorAll('tbody input[type="text"]'); // 모든 합계 입력란
    let totalSum = 0;
    totals.forEach(function (element) {
        totalSum += parseFloat(element.value || 0); // 빈 값이거나 문자열을 0으로 처리
    });
    return totalSum.toFixed(0);
}