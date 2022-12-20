<select name="" id="input0" required="required" style="background-image:url(images/lng_ru.png);">
    <option  style="background-image:url(images/lng_ru.png);" value="rus">rus</option>
    <option  style="background-image:url(images/lng_eng.png);" value="eng">eng</option>
</select>

<script>
$('#input0').click(function() {
 if($("select#input0 :selected").val() == "rus") {
    $("select#input0").attr('style', 'background-image:url(images/lng_ru.png);');
 }
 if($("select#input0 :selected").val() == "eng") {
    $("select#input0").attr('style', 'background-image:url(images/lng_eng.png);');
 }
    console.log('select color: '+$("select#input0 :selected").val());
});
</script>