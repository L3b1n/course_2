.dropbtn {
    padding: 5px;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    width: 140px;
}

.dropbtn
{
  background: url('http://icons.iconarchive.com/icons/custom-icon-design/flag-3/16/Russia-Flag-icon.png') no-repeat left center;
  padding-left: 25px;
  width: 40px;
}

.dropbtn::after {
    background: rgba(0, 0, 0, 0) url("https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_down_48px-16.png") no-repeat scroll center center;
    content: "";
    height: 16px;
    position: absolute;
    right: 0;
    top: 7px;
    width: 16px;
}

.dropdown-content a:first-child
{
  background: url('http://icons.iconarchive.com/icons/custom-icon-design/flag-3/16/Russia-Flag-icon.png') no-repeat left center;
}

.dropdown-content a:last-child
{
  background: url('https://icons.iconarchive.com/icons/fatcow/farm-fresh/16/flag-usa-icon.png') no-repeat left center;
}

.dropdown-content a {
    color: black;
    padding: 2px 0 2px 25px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}


<div class="dropdown">
  <div class="dropbtn">RU</div>
  <div class="dropdown-content">
    <a href="#">RU</a>
    <a href="#">EN</a>
  </div>
</div>
