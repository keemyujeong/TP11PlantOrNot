package com.kyjsoft.tp11plantornot

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

// 기상청
data class WeatherResponse(val response: Weather)
data class Weather(val body: Body)
data class Body(val dataType : String, val items : Items, val totalCount : Int)
data class Items(val item : MutableList<Item>)

data class Item(
    var category : String,
    var fcstValue : String
)


// 병충해
@Xml(name = "service")
data class Service(
    @Element(name = "list")
    val list : List
)
@Xml(name = "list")
data class List(
    @Element(name = "item")
    val item: MutableList<InsectItem>
)
@Xml
data class InsectItem(
    @PropertyElement(name = "insectKorName")
    var insectKorName : String,
    @PropertyElement(name = "oriImg")
    var oriImg : String,
    @PropertyElement(name = "cropName")
    var cropName : String
)

// 농작업 정보
@Xml(name = "response")
data class Response(
    @Element(name= "body")
    var body : FarmBody
)
@Xml(name = "body")
data class FarmBody(
    @Element(name = "item")
    var item: FarmItem
)

@Xml(name = "item")
data class FarmItem(
    @PropertyElement(name = "kidofcomdtySeCodeNm")
    var kidofcomdtySeCodeNm : String,
    @PropertyElement(name = "cn")
    var cn : String
)


// 정보 공유 게시판
data class BoardRecyclerItem (
    var imgUrl: String, // 프로필 사진
    var name: String,
    var selectPlant : String, // 관심작물
    var title : String,
    var text : String,
    var date : String
)

data class BoardDBItem(
    var no: Int,
    var id: String,
    var title : String,
    var text : String,
    var file : String, // 게시글 첨부 파일(사진)
    var date: String
)




















