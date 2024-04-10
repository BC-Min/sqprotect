//장바구니 담기 후 장바구니로 이동여부

function cartadd(event) {
    let row = event.target.closest("tr");
    let ordermenu = row.querySelector("td:nth-child(2)").textContent;
    let orderprice = row.querySelector("td:nth-child(3)").textContent.replace(/[^0-9]/g, '');
    let orderqunt = row.querySelector("td:nth-child(4) input").value;
    let confirmation = confirm(ordermenu+"를 담았습니다. 장바구니로 이동하시겠습니까?");


    if(confirmation){
    window.location.href = "/cart?orderid=aa1&ordermenu="+ordermenu+"&orderprice="+orderprice+"&orderqunt="+orderqunt+"&conf=true";
    } else {
    window.location.href = "/cart?orderid=aa1&ordermenu="+ordermenu+"&orderprice="+orderprice+"&orderqunt="+orderqunt+"&conf=false";
    }
}