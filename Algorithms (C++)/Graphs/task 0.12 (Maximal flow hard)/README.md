<!-- <div class="dropdown">
  <div class="dropbtn">RU</div>
  <div class="dropdown-content">
    <a href="#">RU</a>
    <a href="#">EN</a>
  </div>
</div>

<select name="" id="input0" required="required" style="background-image:url(images/lng_ru.png);">
    <option  style="background-image:url(images/lng_ru.png);" value="rus">rus</option>
    <option  style="background-image:url(images/lng_eng.png);" value="eng">eng</option>
</select>

<script>
('#input0').click(function() {
 if(("select#input0 :selected").val() == "rus") {
    ("select#input0").attr('style', 'background-image:url(images/lng_ru.png);');
 }
 if(("select#input0 :selected").val() == "eng") {
    ("select#input0").attr('style', 'background-image:url(images/lng_eng.png);');
 }
    console.log('select color: '+$("select#input0 :selected").val());
});
</script> -->

<!-- <div class="lang_buttons">
  <a href="#" class="button rus_lang">
	  Английский
	</a>
	<a href="#" class="button en_lang">
		Russian
	</a>
</div>

<div class="rus_lang">
  <p>Далеко-далеко за словесными горами в стране, гласных и согласных живут рыбные тексты. Пустился снова, силуэт сих снова не коварный за языкового текста до рекламных, агенство возвращайся, буквенных жизни это, первую переулка правилами?</p>
<p>Наш, приставка страна единственное большой, власти, злых, взобравшись семь рот он алфавит осталось вершину курсивных сих всемогущая буквенных предупредила залетают необходимыми эта но вдали имеет ты? Предупреждал повстречался, но которое!</p>
<p>Злых, знаках свою. Выйти по всей рукописи запятой дороге маленький дорогу, все вопроса переулка необходимыми точках повстречался текстов наш приставка одна над страна последний, за имеет ты всемогущая предложения рот скатился.</p>
</div>
<div class="en_lang">
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem quos itaque illo quod, similique, architecto. Quis praesentium, qui, pariatur reprehenderit necessitatibus nemo! Id, culpa, sapiente. Nam, officia vitae natus doloremque!</p>
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem quos itaque illo quod, similique, architecto. Quis praesentium, qui, pariatur reprehenderit necessitatibus nemo! Id, culpa, sapiente. Nam, officia vitae natus doloremque!</p>
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem quos itaque illo quod, similique, architecto. Quis praesentium, qui, pariatur reprehenderit necessitatibus nemo! Id, culpa, sapiente. Nam, officia vitae natus doloremque!</p>
</div> -->

<!-- <!DOCTYPE html>
<html >
<head ></head>
<body><select class="lang">
    <option value="ru">ru</option>
    <option value="ua">ua</option>
    </select>
</body>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const select = document.querySelector(".lang");
        if(!select.value) return;
        if(${select.value} == "ru") {
            <p>Привет</p>
        }
        if(${select.value} == "ua") {
            <p>Hello</p>
        }
        }
    );
</script>
</html> -->

<button class="ru">RU</button>
<button class="en">EN</button>
<div data-en="Hello" data-ru="Привет">Привет</div>
<div data-en="Bye" data-ru="Пока">Пока</div>
<div data-en="how are you" data-ru="как дела">как дела</div>