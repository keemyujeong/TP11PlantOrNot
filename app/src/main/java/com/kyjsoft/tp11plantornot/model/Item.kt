package com.kyjsoft.tp11plantornot.model


// 기상청
data class WeatherResponse(val response: Weather)
data class Weather(val body: Body)
data class Body(val dataType : String, val items : Items, val totalCount : Int)
data class Items(val item : MutableList<Item>)

data class Item(
    var category : String,
    var fcstValue : String
)

// weatherXY.json Item 객체
data class WeatherItem (
    var region1 : String,
    var region2 : String,
    var region3 : String,
    var nx : String,
    var ny : String
)


// 병충해 파싱 정보
data class BugRecyclerItem(var plantName : String?, var InsectName : String?, var bugImgUrl : String?)

// 관심작물 선택
data class PickRecyclerItem (var plant:String)

// 관심 작물 농작업 일정
data class HomeRecyclerItem(var title: String, var text: String)



// 정보 공유 게시판
data class BoardRecyclerItem (
    var boardno: Int,
    var imgurl: String, // 프로필 사진
    var name: String,
    var plant : String, // 관심작물
    var title : String,
    var text : String,
    var file : String, // 게시글 첨부 파일(사진)
    var date : String
)

// 내 프로필
data class ProfileItem(
    var no : String,
    var id : String,
    var imgurl : String,
    var name : String,
    var plant : String
)


// 내가 쓴 글 관리
data class MyPostRecyclerItem (
    var boardno : Int,
    var title : String,
    var text : String,
    var date : String,
    var like_count : Int
    )






// 지도 json 데이터
data class KakaoMapData(
    val documents: MutableList<Document>
)
data class Document(
    val address: Address,
    val address_name: String,
    val address_type: String,
    val x: String,
    val y: String
)
data class Address(
    val address_name: String,
    val b_code: String,
    val h_code: String,
    val main_address_no: String,
    val mountain_yn: String,
    val region_1depth_name: String,
    val region_2depth_name: String,
    val region_3depth_h_name: String,
    val region_3depth_name: String,
    val sub_address_no: String,
    val x: String,
    val y: String
)


data class AndroidDeveloper(
    val nickname: String,
    val name : String,
    val dateOfBirth : String,
    val phoneCall : String,
    val Email : String,
)





















