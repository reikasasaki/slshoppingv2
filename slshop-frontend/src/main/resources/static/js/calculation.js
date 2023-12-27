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

document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll('.btn-secondary');

    buttons.forEach(function(button) {
        button.addEventListener('click', function() {
            var parentTd = button.closest('td');
            var quantityInput = parentTd.querySelector('.form-control');

            if (button.id === 'decrement_') {
                let currentNumber = parseInt(quantityInput.value);
                quantityInput.value = Math.max(currentNumber - 1, 1);
            } else if (button.id.startsWith('increment_')) {
                let currentNumber = parseInt(quantityInput.value);
                quantityInput.value = Math.min(currentNumber + 1, 10);
            }
        });
    });
});




