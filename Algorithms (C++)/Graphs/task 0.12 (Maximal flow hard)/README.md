<!-- <style>
.del { display: none; }
.del:not(:checked) + label + * { display: none; }
.del:not(:checked) + label,
.del:checked + label {
display: inline-block;
padding: 2px 10px;
border-radius: 2px;
color: #fff;
background: #4e6473;
cursor: pointer;
}
.del:checked + label {
background: #e36443;
}
</style>

<input type="checkbox" id="raz" class="del" checked="checked"/>
<label for="raz" class="del">нажать</label>
<div>исчезающий текст</div> -->

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<h1>Переключение текста</h1>

<p>Нажмите кнопку, чтобы поменять местами текст элемента DIV:</p>

<p><button onclick="myFunction()">Нажмите</button></p>

<div id="myDIV">Привет</div>

<script>
function myFunction() {
  var x = document.getElementById("myDIV");
  if (x.innerHTML === "Привет") {
    x.innerHTML = "Замененный текст!";
  } else {
    x.innerHTML = "Привет";
  }
}
</script>

</body>
</html>