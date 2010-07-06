<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html;charset=ISO-8859-1">
<title>Mapa de Google</title>
<script src="http://maps.google.com/maps?file=api&v=2&key=ABQIAAAAUnJY3ChJhF0YgyTSDJuVfBTqu-zEVMNfNVaqfAe9FKyfKhfBExSs9LrIQ7GOuBeSnaddg05sRmEBxQ"
type="text/javascript">
</script>

<style type="text/css">
body { font: normal 62.5% verdana; }
 
ul.menubar{
  margin: 0px;
  padding: 0px;
  background-color: #FFFFFF; /* IE6 Bug */
  font-size: 100%;
}
 
ul.menubar .submenu{
  margin: 0px;
  padding: 0px;
  list-style: none;
  background-color: #FFFFFF;
  border: 1px solid #ccc;
  float:left;
}
 
ul.menubar ul.menu{
  display: none;
  position: absolute;
  margin: 0px;
}
 
ul.menubar a{
  padding: 5px;
  display:block;
  text-decoration: none;
  color: #777;
  padding: 5px;
}
 
ul.menu, ul.menu ul{
  margin: 0;
  padding: 0;
  border-bottom: 1px solid #ccc;
  width: 150px; /* Width of Menu Items */
  background-color: #FFFFFF; /* IE6 Bug */
}
 
ul.menu li{
  position: relative;
  list-style: none;
  border: 0px;
}
 
ul.menu li a{
  display: block;
  text-decoration: none;
  border: 1px solid #ccc;
  border-bottom: 0px;
  color: #777;
  padding: 5px 10px 5px 5px;
}
 
ul.menu li sup{
  font-weight:bold;
  font-size:7px;
  color: red;
}
 
/* Fix IE. Hide from IE Mac \*/
* html ul.menu li { float: left; height: 1%; }
* html ul.menu li a { height: 1%; }
/* End */
 
ul.menu ul{
  position: absolute;
  display: none;
  left: 149px; /* Set 1px less than menu width */
  top: 0px;
}
 
ul.menu li.submenu ul { display: none; } /* Hide sub-menus initially */
 
ul.menu li.submenu { background: transparent url(arrow.gif) right center no-repeat; }
 
ul.menu li a:hover { color: #E2144A; }
 
</style>
 
<script type="text/javascript">
function horizontal() {
 
   var navItems = document.getElementById("menu_dropdown").getElementsByTagName("li");
    
   for (var i=0; i< navItems.length; i++) {
      if(navItems[i].className == "submenu")
      {
         if(navItems[i].getElementsByTagName('ul')[0] != null)
         {
            navItems[i].onmouseover=function() {this.getElementsByTagName('ul')[0].style.display="block";this.style.backgroundColor = "#f9f9f9";}
            navItems[i].onmouseout=function() {this.getElementsByTagName('ul')[0].style.display="none";this.style.backgroundColor = "#FFFFFF";}
         }
      }
   }
 
}
</script>

<script type="text/javascript">
//<![CDATA[

function load() {
   if (GBrowserIsCompatible()) {
      var map = new GMap2(document.getElementById("map"));   
      map.setCenter(new GLatLng(-7.23,-35.9),3);   
      map.addControl(new GLargeMapControl());
      map.setMapType(G_NORMAL_MAP);
      
      var point = new GPoint (-7.23,-35.9);
      var marker = new GMarker(point);
      map.addOverlay(marker);
      
      GEvent.addListener(map, "click", function (overlay,point){
         if (point){
            marker.setPoint(point);
            document.posicao.x.value=point.x
            document.posicao.y.value=point.y
         }
      });
   }
}

function chama(){
	load();
	horizontal();
	
}
//]]>
</script>
</head>

<body onload="chama();">
<table
 style="width: 780px; height: 300px; text-align: center; margin-left: auto; margin-right: auto;"
 border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td >
		<div style="z-order:10">
<ul id="menu_dropdown" class="menubar">
   <li class="submenu"><a href="#">HOME</a></li>
   <li class="submenu"><a href="#">Cadastrar Foco</a></li>
   <li class="submenu"><a href="#">Cadastrar Agente</a></li>
   <li class="submenu"><a href="#">Pessoas Contaminadas</a></li>
   <li class="submenu"><a href="#">Buscar</a></li>
</ul>
</div>
</td>
</tr>
		<tr>
		<td style="width: 765px; align: center; height: 280px">
		  <div id="map" style="width: 780px; height: 285px"></div>
		   <div id="formulario" style="margin: 10px">
		   <form action="agente" id="agente" name="agente">
		   Nome <input type="text" name="nome" value="" />
		   <br />
		   Matricula <input type="text" name="matricula" value="" />
		   Area <input type="text" name="area" value="" />
		   </form>
		   </div>		
		</td>
		</tr>
</table>
</body>
</html>
