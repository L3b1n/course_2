<script>
<style>
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
</script>

<input type="checkbox" id="raz" class="del" checked="checked"/>
<label for="raz" class="del">нажать</label>
<div>исчезающий текст</div>