/**
 * 
 */

//function updateResult(index) {
//        var price = document.getElementById("price" + index).textContent;
//        var quantity = document.getElementById("quantity" + index).value;
//        var resultElement = document.getElementById("result" + index);
//
//        var result = parseFloat(price) * parseInt(quantity);
//        resultElement.textContent = result;
//    
window.onload = function() {

    var buttons = document.querySelectorAll('.btn-secondary');

    buttons.forEach(function(button) {
        button.addEventListener('click', function() {
            var parentTr = button.closest('tr');
            var quantityInput = parentTr.querySelector('.quantity');
            var rowNumber = parentTr.rowIndex - 1; // 0-based index
            var priceElement = parentTr.querySelector('.price');

            if (button.id.startsWith('decrement_')) {
                let currentNumber = parseInt(quantityInput.value);
                quantityInput.value = Math.max(currentNumber - 1, 1);

            } else if (button.id.startsWith('increment_')) {
                let currentNumber = parseInt(quantityInput.value);
                quantityInput.value = Math.min(currentNumber + 1, 10);
            }

            // 小計を再計算
            var subTotalElement = parentTr.querySelector('.subtotal');
            subTotalElement.textContent = priceElement.textContent * quantityInput.value;
            
            var subTotalElements = document.querySelectorAll('.subtotal');
            var totalVal = 0;
            for (var i = 0, len = subTotalElements.length; i < len; i++) {
  				totalVal += Number(subTotalElements[i].textContent);
			}
            
            var totalElement = document.querySelector('.sum');
            totalElement.textContent = '合計'+ totalVal+ "円";
        });
    });
};


//    function updateSubtotal(rowNumber) {
//        var parentTrs = document.querySelectorAll('.text-center');
//        var tr = parentTrs[rowNumber];
//        var quantityInput = tr.querySelector('.form-control');
//        var priceElement = tr.querySelector('#price');
//        var subtotalElement = tr.querySelector('#subtotal_' + rowNumber);
//
//        var quantity = parseInt(quantityInput.value);
//        var price = parseFloat(priceElement.textContent);
//        var subtotal = quantity * price;
//
//        // 小数点以下を表示させないように整数に変換
//        subtotalElement.textContent = Math.round(subtotal);
//    }
//}





