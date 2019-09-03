<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<img id="map" src="../img/map/${comeRouteList.id }_map.png">
<h3 id="come_description">${comeRouteList.description }</h3>
<p id="come_place_lot"><img id="location_img" src="../img/map/location.png"> ${comeRouteList.place_lot }</p>
<span id="address_text">지번</span> 
<span id="come_place_street">${comeRouteList.place_street }</span>
<p id="come_place_name">${comeRouteList.place_name }</p>
<p id="come_tel"><img id="call_img" src="../img/map/call.png"> ${comeRouteList.tel }</p>
<div class="routeArea">
	<img class="searchRoute_img" src="../img/map/searchRoute.png">
	<p class="searchRoute_text">길찾기</p>
	<div id="line">|</div>
	<img class="navigation_img" src="../img/map/navigation.png">
	<p class="navigation_text">내비게이션</p>
</div>