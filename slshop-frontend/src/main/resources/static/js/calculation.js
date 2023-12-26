/**
 * 
 */

function updateResult(index) {
        var price = document.getElementById("price" + index).textContent;
        var quantity = document.getElementById("quantity" + index).value;
        var resultElement = document.getElementById("result" + index);

        var result = parseFloat(price) * parseInt(quantity);
        resultElement.textContent = result;
    }