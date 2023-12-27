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
            var quantityInput = parentTr.querySelector('.form-control');
            var rowNumber = parentTr.rowIndex - 1; // 0-based index
            var priceElements = document.getElementsByClassName("class");

            if (button.id.startsWith('decrement_')) {
                let currentNumber = parseInt(quantityInput.value);
                quantityInput.value = Math.max(currentNumber - 1, 1);

            } else if (button.id.startsWith('increment_')) {
                let currentNumber = parseInt(quantityInput.value);
                quantityInput.value = Math.min(currentNumber + 1, 10);
            }

            // 小計を再計算
            var parentTrs = document.querySelectorAll('.text-center');
            var tr = parentTrs[rowNumber];
            var quantityInputs = tr.querySelectorAll('.form-control');
            var subtotalElement = tr.querySelector('#subtotal_' + rowNumber);

            if (priceElements.length > 0) {
                let subtotal = 0;

                for (let i = 0; i < priceElements.length; i++) {
                    var price = parseInt(priceElements[i].value); // 価格がtextContentに格納されていると仮定
                    var quantity = parseInt(quantityInputs[i].value);
                    subtotal += quantity * price;
                }

                subtotalElement.textContent = Math.round(subtotal);
            }
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





