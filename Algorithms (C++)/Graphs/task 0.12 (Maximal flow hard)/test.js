(function($) {

    function Translate() { 
      //initialization
      this.init =  function(attribute, lng) {
        this.attribute = attribute;
        this.lng = lng;
      };
      //translate 
      this.process = function() {
        var _self = this;
        var xrhFile = new XMLHttpRequest();
        //load content data 
        xrhFile.open("GET", "./translations/"+this.lng+".json", false);
        xrhFile.onreadystatechange = function () {
          if(xrhFile.readyState === 4) {
            if(xrhFile.status === 200 || xrhFile.status == 0) {
              var LngObject = JSON.parse(xrhFile.responseText);
              console.log(LngObject["name1"]);
              var allDom = document.getElementsByTagName("*");
              for(var i =0; i < allDom.length; i++){
                var elem = allDom[i];
                var key = elem.getAttribute(_self.attribute);
                if(key != null) {
                  console.log(key);
                  elem.innerHTML = LngObject[key];
                }
              }
            }
          }
        };
        xrhFile.send();
      };
    }
    
    // Change language
    function loadNewLang(new_lang) {
      var translate = new Translate();
      var currentLng = new_lang;
      var attributeName = 'data-lang';
      translate.init(attributeName, currentLng);
      translate.process();
    }
    
    loadNewLang('ua');
    
    $('.language-switcher').on('click', function(e) {
      e.preventDefault();
      $('.preloader').removeAttr('style');
      if($(this).hasClass('ru')) {
        $(this).toggleClass('ru ua').text('UA');
        loadNewLang('ua');
        ln_preloader();
      } else if ($(this).hasClass('ua')) {
        $(this).toggleClass('ua ru').text('RU');
        loadNewLang('ru');
        ln_preloader();
      }
    });
    
    })(jQuery);